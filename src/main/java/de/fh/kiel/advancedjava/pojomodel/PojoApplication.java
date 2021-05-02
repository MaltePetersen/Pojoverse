package de.fh.kiel.advancedjava.pojomodel;

import de.fh.kiel.advancedjava.pojomodel.service.CreateExamplesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@SpringBootApplication
public class PojoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PojoApplication.class, args);
		new CreateExamplesService().createExamplePojoBase64();

		var path = Paths.get("/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/exploit/Main.class");

		var read = Files.readAllBytes(path);
		loadClass(read);


	}
	public static String loadData(String location) throws IOException {
		return Files.readString(Paths.get(location));
	}

	public static Class<?> loadClass(byte[] clazz){
		return new ClassLoader(){
			public Class<?> defineClass(byte[] b) {
				return defineClass(null, b, 0, b.length);
			}
		}.defineClass(clazz);
	}

}
