package de.fh.kiel.advancedjava.pojomodel.facade;

import de.fh.kiel.advancedjava.pojomodel.dto.AddAttributeDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExistsException;
import de.fh.kiel.advancedjava.pojomodel.model.Package;
import de.fh.kiel.advancedjava.pojomodel.model.*;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PackageRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import de.fh.kiel.advancedjava.pojomodel.service.PackageService;
import de.fh.kiel.advancedjava.pojomodel.util.ParseUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This facade wraps all pojo db transactions because Spring Data Neo4j does not support stuff like
 * cascading deletes (https://community.neo4j.com/t/deletebyid-is-not-deleting-exisiting-relationships/38376/2)
 * and save only updates if we use optimistic Locking or use the generated value property (but this will also lead to the similar
 * problems down the line).Therefore this facade serves as an abstraction layer between Spring Data Neo4j and this application.
 * This layer can not be split into a pojo and attribute layer because pojo and attribute have so much in common that this would cause
 * circular dependencies.
 **/
@Service
public class PojoFacadeService {

    private static final String LIST_TYPE = "java.util.List";

    private final PackageRepository packageRepository;
    private final PojoRepository pojoRepository;
    private final AttributeRepository attributeRepository;
    private final PackageService packageService;

    public PojoFacadeService(PackageRepository packageRepository, PojoRepository pojoRepository, AttributeRepository attributeRepository, PackageService packageService) {
        this.pojoRepository = pojoRepository;
        this.attributeRepository = attributeRepository;
        this.packageService = packageService;
        this.packageRepository = packageRepository;
    }

    public Pojo createPojo(Pojo pojo) {
        return createPojo(pojo.getCompletePath(), pojo.getClassName(), pojo.getAPackage().getId(), pojo.getParentClass().getCompletePath(), pojo.getParentClass().getClassName(), pojo.getParentClass().getAPackage().getId(), pojo.getInterfaces(), pojo.getAttributes());
    }

    public Pojo createPojo(PojoInfo pojoInfo) {
        var attributes = pojoInfo.getAttributeInfos().stream().map(attributeInfo -> createAttribute(attributeInfo, pojoInfo.getCompletePath())).collect(Collectors.toSet());

        return createPojo(pojoInfo.getCompletePath(), pojoInfo.getClassName(), pojoInfo.getPackageName(), pojoInfo.getSuperClassCompletePath(), pojoInfo.getSuperClassName(), pojoInfo.getSuperClassPackageName(), pojoInfo.getInterfaces(), attributes);
    }

    public Pojo createPojo(String completePath, String className, String packageName, String superCompletePath, String superClassName, String superPackageName, Set<String> interfaces, Set<Attribute> attributes) {

        pojoExistsAndIsNotEmptyHull(completePath);

        var pojo = pojoRepository.findById(completePath).orElseGet(() -> Pojo.builder().completePath(completePath)
                .className(className)
                .aPackage(packageService.createPackage(packageName))
                .interfaces(Collections.emptySet())
                .attributes(Collections.emptySet())
                .build());
        var superClass = createPojo(superCompletePath, superClassName, superPackageName);

        pojo.setParentClass(superClass);
        pojo.setInterfaces(interfaces);
        pojo.setAttributes(attributes);
        pojo.setEmptyHull(false);

        return save(pojo);
    }

    public Pojo createEmptyHull(String completePath, String className, String packageName) {
        return pojoRepository.save(createPojo(completePath, className, packageName));
    }

    private Pojo createPojo(String completePath, String className, String packageName) {
        return pojoRepository.findById(completePath).orElseGet(() -> Pojo.builder()
                .completePath(completePath)
                .className(className)
                .aPackage(this.packageService.createPackage(packageName))
                .interfaces(Collections.emptySet())
                .attributes(Collections.emptySet())
                .emptyHull(true).build());
    }

    private void pojoExistsAndIsNotEmptyHull(String completePath) {
        pojoRepository.findById(completePath).ifPresent(pojo -> {
            if (!pojo.isEmptyHull()) {
                throw new PojoAlreadyExistsException(pojo.getCompletePath());
            }
        });
    }

    public Attribute createAttribute(String name, String dataTypeCompletePath, String accessModifier, String className, String packageName, String pojoCompletePath) {
        var id = generateAttributeId(pojoCompletePath, name);
        return attributeRepository.findById(id).orElseGet(() ->
                Attribute.builder().id(id).name(name).accessModifier(accessModifier).clazz(createPojo(dataTypeCompletePath, className, packageName)).build());
    }

    public Attribute createAttribute(AttributeInfo attributeInfo, String pojoCompletePath) {
        return createAttribute(attributeInfo.getName(), attributeInfo.getDataTypeName(), attributeInfo.getAccessModifier(), attributeInfo.getClassName(), attributeInfo.getPackageName(), pojoCompletePath);
    }

    public Pojo addAttribute(AddAttributeDTO addAttributeDTO, Pojo pojo) {

        var dataType = transformPrimitivesAndFindIfThePojoExists(addAttributeDTO.getType());

        var attribute = createAttribute(addAttributeDTO.getName(), dataType.getCompletePath(), addAttributeDTO.getVisibility(), dataType.getClassName(), dataType.getAPackage().getId(), pojo.getCompletePath());

        addGenericListInformationIfNeeded(dataType, attribute, addAttributeDTO);

        pojo.getAttributes().add(attribute);
        return save(pojo);
    }

    private void addGenericListInformationIfNeeded(Pojo dataType, Attribute attribute, AddAttributeDTO addAttributeDTO) {
        if (dataType.getCompletePath().equals(LIST_TYPE)) {

            var pojoOfGenericType = transformPrimitivesAndFindIfThePojoExists(addAttributeDTO.getGenericType());
            attribute.setGenericType(pojoOfGenericType);
        }
    }

    private Pojo transformPrimitivesAndFindIfThePojoExists(String pojoCompletePath) {
        String transformedTypes = Primitiv.getWrapperByPrimitive(pojoCompletePath);
        return createPojo(transformedTypes, ParseUtil.parseClassName(transformedTypes), ParseUtil.parsePackageName(transformedTypes));
    }

    public String generateAttributeId(String pojoId, String attributeName) {
        return pojoId + attributeName;
    }

    public Package createPackage(String packageName) {
        return packageService.createPackage(packageName);
    }

    private Pojo save(Pojo pojo) {
        var attributes = attributeRepository.findAllByClazz_CompletePath(pojo.getCompletePath());
        attributes.forEach(attribute -> attribute.setClazz(pojo));
        pojoRepository.delete(pojo);
        var saved = pojoRepository.save(pojo);
        attributeRepository.saveAll(attributes);
        return saved;
    }

    /**
     * This methods delete a pojo it needs to use delete all attributes manually because Spring Data Neo4j currently does not
     * support cascading deletes:
     * https://community.neo4j.com/t/deletebyid-is-not-deleting-exisiting-relationships/38376/2
     */
    public void delete(Pojo pojo) {
        pojo.getAttributes().forEach(attributeRepository::delete);
        if (!attributeRepository.findAllByClazz_CompletePath(pojo.getCompletePath()).isEmpty()) {
            pojo.setAttributes(Collections.emptySet());
            pojo.setEmptyHull(true);
            save(pojo);
        } else
            pojoRepository.delete(pojo);
    }

    public Pojo deleteAttributeFromPojo(Pojo pojo, Attribute attribute) {

        pojo.getAttributes().remove(attribute);
        attributeRepository.delete(attribute);

        return save(pojo);
    }

    public void deleteAllRessources() {
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
        packageRepository.deleteAll();
    }

}
