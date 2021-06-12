package de.fh.kiel.advancedjava.pojomodel;


import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * With this class new base64 classes can easily be created to test the api
 * the user just needs to switch the path and copy the String which is printed out
 */
public class CreateBase64Classes {


    public static void main(String[] args) throws IOException {
        var logger = LoggerFactory.getLogger(CreateBase64Classes.class);
        byte[] data = Files.readAllBytes(Paths.get("/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/exploit/Main.class"));
        logger.info(Base64.getEncoder().encodeToString(data));
    }

}
