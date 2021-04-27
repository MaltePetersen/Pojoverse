package de.fh.kiel.advancedjava.pojomodel.service;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class CreateExamplesService {

    public void  createExamplePojoBase64() throws IOException {
        byte[] data = Files.readAllBytes(Paths.get("/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/class/Interfaces.class"));
        String test = Base64.getEncoder().encodeToString(data);
        System.out.println(test);
    }
}
