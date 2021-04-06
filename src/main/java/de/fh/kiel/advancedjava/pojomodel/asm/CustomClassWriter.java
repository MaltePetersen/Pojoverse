package de.fh.kiel.advancedjava.pojomodel.asm;

import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.Base64;

public class CustomClassWriter {

    static String className = "";
    byte data[];
    ClassReader reader;
    ClassWriter writer;
//TODO ASM Docu 2.2.2 (Seite 14) durchlesen, könnten wir statt
    public CustomClassWriter() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        data = Files.readAllBytes(Paths.get("/Users/mpetersen/Documents/pojo/src/main/java/fh/kiel/pojo/asm/Ticket.class"));
        String  test = Base64.getEncoder().encodeToString(data);
        InputStream text = new ByteArrayInputStream(Base64.getDecoder().decode(test));
        reader = new ClassReader(text);
        Class<?> clazz =      new DynamicClassLoader().defineClass(data );
        Object obj =  clazz.getDeclaredConstructor().newInstance();
    }


}
