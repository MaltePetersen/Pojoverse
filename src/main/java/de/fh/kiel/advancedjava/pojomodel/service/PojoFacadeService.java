package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExists;
import de.fh.kiel.advancedjava.pojomodel.model.Package;
import de.fh.kiel.advancedjava.pojomodel.model.*;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.apache.tomcat.jni.Error;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Das Facade soll die Komplexität der Datenbank Operation, die durch die Nutung von Optimistic Locking
 * auffangen (@Version...). Der Rest der Services soll nicht die wissen müssen wie die Daten abgefragt werden vor der
 * Nutzung.
 * Vorher wurde ein PojoService und ein AttributeService genutzt. Mit diesem Ansatz bräuchten beide Service sich gegenseitg,
 * weil ein Pojo Abhängigkeiten zu Attributes hat und ein Attribute durch den Datentyp ebenfalls Pojo erzeugen muss.
 * Wodurch eine zyklische Dependency entsteht, dies macht klar das dieser Designansatz für dieses Objekt nicht das richtige ist
 * und deshalb eine Ebene die alls zusammenfasst aber seine Komplexität von der Rest der Applikation kapselt
 */
@Service
public class PojoFacadeService {

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

    public Pojo createPojo(String completePath, String className, String packageName) {
        return pojoRepository.findById(completePath).orElseGet(() -> pojoRepository.save(Pojo.builder()
                .completePath(completePath)
                .className(className)
                .aPackage(this.packageService.createPackage(packageName))
                .interfaces(Collections.emptySet())
                .attributes(Collections.emptySet())
                .emptyHull(true).build()));
    }
    private void pojoExistsAndIsNotEmptyHull(String completePath){
        pojoRepository.findById(completePath).ifPresent(pojo-> { if(! pojo.isEmptyHull()){
            throw new PojoAlreadyExists(pojo.getCompletePath());
        } } );
    }

    public Pojo createPojo(String completePath, String className, String packageName, String superCompletePath, String superClassName, String superPackageName, Set<String> interfaces, Set<Attribute> attributes) {

        pojoExistsAndIsNotEmptyHull(completePath);

        var pojo = pojoRepository.findById(completePath).orElseGet( () ->Pojo.builder().completePath(completePath)
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

        return pojoRepository.save(pojo);
    }

    public Attribute createAttribute(String name, String dataTypeName, String accessModifier, String className, String packageName, String pojoCompletePath) {
        var id = pojoCompletePath + "" + name;
        return attributeRepository.findById(id).orElseGet(() ->
                Attribute.builder().id(id).name(name).accessModifier(accessModifier).clazz(createPojo(dataTypeName ,className, packageName)).build());

    }

    public Attribute createAttribute(AttributeInfo attributeInfo, String pojoCompletePath) {
        return createAttribute(attributeInfo.getName(), attributeInfo.getDataTypeName(), attributeInfo.getAccessModifier(), attributeInfo.getClassName(), attributeInfo.getPackageName(), pojoCompletePath);
    }

    public Package createPackage(String packageName) {
        return packageService.createPackage(packageName);
    }
}
