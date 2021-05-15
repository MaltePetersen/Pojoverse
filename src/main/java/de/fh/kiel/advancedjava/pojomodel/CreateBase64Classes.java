package de.fh.kiel.advancedjava.pojomodel;

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
        byte[] data = Files.readAllBytes(Paths.get("/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/exploit/Main.class"));
        System.out.println(Base64.getEncoder().encodeToString(data));
    }

}
