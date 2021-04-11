package de.fh.kiel.advancedjava.pojomodel;

import de.fh.kiel.advancedjava.pojomodel.service.CreateExamplesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class PojoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PojoApplication.class, args);
	new	CreateExamplesService().createExamplePojoBase64();
	}


}
