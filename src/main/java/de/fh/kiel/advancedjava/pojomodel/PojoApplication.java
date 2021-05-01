package de.fh.kiel.advancedjava.pojomodel;

import de.fh.kiel.advancedjava.pojomodel.service.CreateExamplesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class PojoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PojoApplication.class, args);
		new CreateExamplesService().createExamplePojoBase64();
	}


}
