package de.fh.kiel.advancedjava.pojomodel.facade;

import de.fh.kiel.advancedjava.pojomodel.dto.AddAttributeDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExistsException;
import de.fh.kiel.advancedjava.pojomodel.model.Package;
import de.fh.kiel.advancedjava.pojomodel.model.*;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import de.fh.kiel.advancedjava.pojomodel.service.PackageService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Das Facade soll die Komplexität der Datenbank Operation, die durch die Nutung von Optimistic Locking entstehen,
 * auffangen (@Version...). Der Rest der Services soll nicht die wissen müssen wie die Daten abgefragt werden vor der
 * Nutzung. Gleichzeitig soll dieser Service keine Buisness Funktionalität kennen.
 * Vorher wurde ein PojoService und ein AttributeService genutzt. Mit diesem Ansatz bräuchten beide Service sich gegenseitg,
 * weil ein Pojo Abhängigkeiten zu Attributes hat und ein Attribute durch den Datentyp ebenfalls Pojo erzeugen muss.
 * Wodurch eine zyklische Dependency entsteht, dies macht klar das dieser Designansatz für dieses Objekt nicht das richtige ist
 * und deshalb eine Ebene die alls zusammenfasst aber seine Komplexität von der Rest der Applikation kapselt
 */
@Service
public class PojoFacadeService {

    private static final String LIST_TYPE = "java.util.List";


    private final PojoRepository pojoRepository;
    private final AttributeRepository attributeRepository;
    private final PackageService packageService;

    public PojoFacadeService(PojoRepository pojoRepository, AttributeRepository attributeRepository, PackageService packageService) {
        this.pojoRepository = pojoRepository;
        this.attributeRepository = attributeRepository;
        this.packageService = packageService;
    }

    public Pojo createPojo(Pojo pojo) {
        return createPojo(pojo.getCompletePath(), pojo.getClassName(), pojo.getAPackage().getId(), pojo.getParentClass().getCompletePath(), pojo.getParentClass().getClassName(), pojo.getParentClass().getAPackage().getId(), pojo.getInterfaces(), pojo.getAttributes());
    }

    public Pojo createPojo(PojoInfo pojoInfo) {
        var attributes = pojoInfo.getAttributeInfos().stream().map(attributeInfo -> createAttribute(attributeInfo, pojoInfo.getCompletePath())).collect(Collectors.toSet());

        return createPojo(pojoInfo.getCompletePath(), pojoInfo.getClassName(), pojoInfo.getPackageName(), pojoInfo.getSuperClassCompletePath(), pojoInfo.getSuperClassName(), pojoInfo.getSuperClassPackageName(), pojoInfo.getInterfaces(), attributes);
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
    public Pojo createEmptyHull(String completePath, String className, String packageName){
      return pojoRepository.save(createPojo(completePath, className, packageName));
    }

    private void pojoExistsAndIsNotEmptyHull(String completePath) {
        pojoRepository.findById(completePath).ifPresent(pojo -> {
            if (!pojo.isEmptyHull()) {
                throw new PojoAlreadyExistsException(pojo.getCompletePath());
            }
        });
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

    public Pojo save(Pojo pojo){
        pojoRepository.deleteById(pojo.getCompletePath());
        return pojoRepository.save(pojo);
    }

}
