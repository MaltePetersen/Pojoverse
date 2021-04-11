package de.fh.kiel.advancedjava.pojomodel.service;


import de.fh.kiel.advancedjava.pojomodel.dto.AttributeChangeDTO;
import de.fh.kiel.advancedjava.pojomodel.model.*;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PojoService {

    Logger logger = LoggerFactory.getLogger(PojoService.class);

    private final PojoRepository pojoRepository;
    private final DynamicClassLoaderService dynamicClassLoaderService;

    PojoService(PojoRepository pojoRepository, DynamicClassLoaderService dynamicClassLoaderService) {
        this.pojoRepository = pojoRepository;
        this.dynamicClassLoaderService = dynamicClassLoaderService;
    }

    public Pojo createPojo(byte[] compiledClazz) {

        Class<?> loadedClazz = this.dynamicClassLoaderService.loadClass(compiledClazz);

        Optional<Pojo> pojo = pojoRepository.findById(loadedClazz.getName());
        if (pojo.isEmpty() || pojo.get().isEmptyHull()) {

        var interfaces = extractAndDefineInterfaces(loadedClazz.getInterfaces());
        var attributes = extractAndDefineAttributes(loadedClazz.getDeclaredFields());
        var parentClazz = extractAndDefineParentClass(loadedClazz.getSuperclass());

        Pojo newPojo = new Pojo(loadedClazz.getName(), loadedClazz.getPackageName(), attributes, parentClazz, interfaces);
            pojoRepository.save(newPojo);

            logger.info("The following Pojo was created "+ newPojo);
            return newPojo;
        }
    return null;
    }

    private Set<String> extractAndDefineInterfaces(Class<?>[] interfaces) {
        return Arrays.stream(interfaces).map((Class::toString)).collect(Collectors.toSet());
    }

    private Set<Attribute> extractAndDefineAttributes(Field[] fields){
        return Arrays.stream(fields).map(field -> {
            if(field.getType().isPrimitive()){
                return new Primitive(field.getName(), new PrimitiveDataType(field.getType().getTypeName()), Modifier.toString(field.getModifiers()));
            }else {
                return new Reference(field.getName(), field.getType().getTypeName(), Modifier.toString(field.getModifiers()),this.checkForRootClassAndCreateAppropriateClass(field.getType()) );
            }
        } ).collect(Collectors.toSet());
    }

    private Pojo extractAndDefineParentClass(Class<?> parentClazz){
        Optional<Pojo> pojo =  pojoRepository.findById(parentClazz.getName());
        if(pojo.isPresent() && pojo.get().isEmptyHull())
            return pojo.get();
        return this.checkForRootClassAndCreateAppropriateClass(parentClazz);
    }

    private Pojo checkForRootClassAndCreateAppropriateClass(Class<?> clazz){
        Optional<Pojo> pojo =  pojoRepository.findById(clazz.getName());
        if(pojo.isPresent()){
            return pojo.get();
        }
        if(clazz.getSuperclass() == null )
            return new  Pojo(clazz.getName(), clazz.getPackageName());

        return new Pojo(clazz.getName(), clazz.getPackageName(), this.checkForRootClassAndCreateAppropriateClass(clazz.getSuperclass()));
    }

    public boolean deletePojo(String pojoName){
            if(pojoRepository.existsById(pojoName)){
                pojoRepository.deleteById(pojoName);
                return true;
            }
           return false;
    }
    public boolean changeAttribute(AttributeChangeDTO attributeChangeDTO){
        Optional<Pojo> pojo = pojoRepository.findById(attributeChangeDTO.getClassName());
        if(pojo.isPresent()){
           var attr =  pojo.get().getAttributes().stream().filter((attribute)-> attribute.getName().equals(attributeChangeDTO.getAttributeName())).findFirst();
           if(attr.isPresent()){
               pojo.get().getAttributes().remove(attr.get());
                pojoRepository.deleteById(pojo.get().getClassName());
               pojoRepository.save(pojo.get());
            return true;
           }
        }
        return false;
    }

}
