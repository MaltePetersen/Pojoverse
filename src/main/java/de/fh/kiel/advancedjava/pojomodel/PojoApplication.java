package de.fh.kiel.advancedjava.pojomodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@SpringBootApplication
public class PojoApplication {

	public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
		SpringApplication.run(PojoApplication.class, args);
	}


}
