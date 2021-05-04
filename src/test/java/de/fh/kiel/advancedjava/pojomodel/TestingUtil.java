package de.fh.kiel.advancedjava.pojomodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
@Component
public class TestingUtil {

    private final Map<String, Path> base64Values = new HashMap<>();
    private final Map<String, Path> jsonValues = new HashMap<>();
    private final Map<String, Path> classValues = new HashMap<>();


    private static final String PATH_TO_EXAMPLE_DATA ="/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/";

    private static final String PATH_TO_BASE_64_FOLDER = PATH_TO_EXAMPLE_DATA + "base64Encoded/";

    private static final String PATH_TO_JSON_FOLDER = PATH_TO_EXAMPLE_DATA +"json/";

    private static final String PATH_TO_CLASSES =  PATH_TO_EXAMPLE_DATA + "class/";


    public TestingUtil() throws IOException {
        loadClassesEncodedInBase64();
        loadClassesEncodedInJSON();
        loadClassesFromClassFile();
    }


    private  String loadString(Path path) throws IOException {
        return Files.readString(path);
    }
    private byte[] loadBytes(Path path) throws IOException {
      return Files.readAllBytes(path);
    }
    private Path createPath(String location){
        return Path.of(location);
    }


    private void loadClassesEncodedInBase64() throws IOException {
        base64Values.put("defaultClass", createBase64Path(  "DefaultClass.txt"));
        base64Values.put("classWithPrimtives", createBase64Path(  "ClassWithPrimtives.txt"));
        base64Values.put("notBase64EncodedClass", createBase64Path( "notBase64EncodedClass.txt"));
        base64Values.put("jar", createBase64Path( "Jar.txt"));
    }
    private void loadClassesEncodedInJSON() throws IOException {
        jsonValues.put("badAttributeChangeDTO",createJSONPath( "BadAttributeChangeDTO.json"));
        jsonValues.put("attributeChangeDTO", createJSONPath( "AttributeChangeDTO.json"));
        jsonValues.put("attributeAddDTO", createJSONPath( "AttributeAddDTO.json"));
        jsonValues.put("attributeAddListDTO", createJSONPath( "AttributeAddListDTO.json"));
        jsonValues.put("pojos", createJSONPath( "pojos.json"));
        jsonValues.put("defaultClass", createJSONPath( "defaultClass.json"));
        jsonValues.put("packagesPojos", createJSONPath( "packagesPojos.json"));
        jsonValues.put("emptyHull", createJSONPath( "emptyHull.json"));
        jsonValues.put("defaultClassStats", createJSONPath( "defaultClassStats.json"));
    }
    private Path createJSONPath(String name){
        return createPath(PATH_TO_JSON_FOLDER + name);
    }
    private Path createBase64Path(String name){
        return createPath(PATH_TO_BASE_64_FOLDER + name);
    }
    private Path createClassesPath(String name){
        return createPath(PATH_TO_CLASSES + name);

    }
    private void loadClassesFromClassFile() throws IOException {
        classValues.put("DefaultClass",createClassesPath(  "DefaultClass.class"));
        classValues.put("ClassWithPrimtives",createClassesPath(  "ClassWithPrimtives.class"));
    }
    public String getBase64Value(String name) throws IOException {
        return loadString(base64Values.get(name));
    }
    public String getJSONValue(String name) throws IOException {
        return loadString(jsonValues.get(name));
    }
    public byte[] getClassValue(String name) throws IOException {
        return loadBytes(classValues.get(name));
    }
    public Object createObjectFromJSON(String json, Class<?> clazz) throws JsonProcessingException {
       return new ObjectMapper().readValue(json, clazz);
    }
    public Object createListOrSetFromJSON(String json, TypeReference<?> typeReference) throws JsonProcessingException {
        return new ObjectMapper().readValue(json,typeReference);
    }


}
