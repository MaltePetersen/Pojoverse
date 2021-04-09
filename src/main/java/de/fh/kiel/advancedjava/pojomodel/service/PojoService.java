package de.fh.kiel.advancedjava.pojomodel.service;


import de.fh.kiel.advancedjava.pojomodel.asm.DynamicClassLoader;
import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PojoService {


    private final PojoRepository pojoRepository;


    PojoService(PojoRepository pojoRepository){
    this.pojoRepository = pojoRepository;
    }

    public Pojo createPojo(byte[] file){
        Class<?> clazz = decodeAndLoadClass(file);
        var interfaces = Arrays.stream(clazz.getInterfaces()).map((Class::toString)).collect(Collectors.toSet());
        var attributes = Arrays.stream(clazz.getDeclaredFields()).map(field  -> new Attribute(field.getName() ,field.getType().getTypeName()) ).collect(Collectors.toSet());        ;
        var parentClazz = clazz.getSuperclass();
        var parentClazzClazz = addAlreadyExistingPojo(new Pojo(parentClazz.getName(),parentClazz.getPackageName()));

        Pojo pojo = new Pojo(clazz.getName(), clazz.getPackageName(),attributes,parentClazzClazz ,interfaces);

        System.out.println(pojo);

    if(!pojoRepository.existsByClassName(pojo.getClassName())){
        pojoRepository.save(pojo);
    }
        return  pojo;
    }
    private Class<?> decodeAndLoadClass(byte[] file){
        return  new DynamicClassLoader().defineClass(file);
    }

    private Set<String> extractAndDefineInterfaces( Class<?>[] interfaces){
    return Arrays.stream(interfaces).map((Class::toString)).collect(Collectors.toSet());

    }

    private Pojo addAlreadyExistingPojo(Pojo pojo){
        if(pojoRepository.existsByClassName(pojo.getClassName()))
            return pojoRepository.findByClassName(pojo.getClassName());
        return pojo;
    }


public void  createExamplePojoBase64() throws IOException {
        byte[] data = Files.readAllBytes(Paths.get("/Users/mpetersen/Documents/pojo/src/main/java/fh/kiel/pojo/asm/Ticket.class"));
        String test = Base64.getEncoder().encodeToString(data);
    System.out.println(test);
    }
}
