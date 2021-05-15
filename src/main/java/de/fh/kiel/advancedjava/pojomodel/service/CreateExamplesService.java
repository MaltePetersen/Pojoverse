package de.fh.kiel.advancedjava.pojomodel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class CreateExamplesService {
    Logger logger = LoggerFactory.getLogger(CreateExamplesService.class);

    public CreateExamplesService() throws IOException {
    }

    public void createExamplePojoBase64() throws IOException {
        byte[] data = Files.readAllBytes(Paths.get("/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/exploit/Main.class"));
        var test = Base64.getEncoder().encodeToString(data);
        logger.info(test);
    }
}
