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
		byte[] clazz = Files.readAllBytes(Paths.get("/Users/mpetersen/Desktop/pojo-malte/build/classes/java/main/de/fh/kiel/advancedjava/pojomodel/Demo.class"));

		var a = loadClass(clazz).getMethods();
		System.out.println(a);
	}

	public static Class<?> loadClass(byte[] clazz){
		return new ClassLoader(){
			public Class<?> defineClass(byte[] b) {
				return defineClass(null, b, 0, b.length);
			}
		}.defineClass(clazz);
	}



}
