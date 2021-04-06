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
import java.util.stream.Collectors;

@Service
public class PojoService {
    PojoRepository pojoRepository;


    PojoService(PojoRepository pojoRepository){
    this.pojoRepository = pojoRepository;
    }


    public Pojo createPojo(String file){
        Class<?> clazz = new DynamicClassLoader().defineClass(Base64.getDecoder().decode(file));
        var test = Arrays.stream(clazz.getInterfaces()).map((Class::toString)).collect(Collectors.toSet());
        var attributes = Arrays.stream(clazz.getDeclaredFields()).map(field  -> new Attribute(field.getName() ,field.getType().getTypeName()) ).collect(Collectors.toSet());        ;
        var parentClazz = clazz.getSuperclass();
        Pojo pojo =
                new Pojo(clazz.getName(), clazz.getPackageName(),attributes, new Pojo(parentClazz.getName(),parentClazz.getPackageName()),test
);
        System.out.println(pojo);
       pojoRepository.save(pojo);

        return  pojo;
    }
public void  createExamplePojoBase64() throws IOException {
        byte[] data = Files.readAllBytes(Paths.get("/Users/mpetersen/Documents/pojo/src/main/java/fh/kiel/pojo/asm/Ticket.class"));
        String test = Base64.getEncoder().encodeToString(data);
    System.out.println(test);
    }
}
