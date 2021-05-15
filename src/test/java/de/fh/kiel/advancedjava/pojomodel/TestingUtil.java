package de.fh.kiel.advancedjava.pojomodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.fh.kiel.advancedjava.pojomodel.model.*;
import de.fh.kiel.advancedjava.pojomodel.model.Package;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class TestingUtil {

    private final Map<String, Path> base64Values = new HashMap<>();
    private final Map<String, Path> jsonValues = new HashMap<>();
    private final Map<String, Path> classValues = new HashMap<>();
    private final Map<String, Pojo> pojos = new HashMap<>();
    private final Map<String, Pojo> emptyHulls = new HashMap<>();
    private final Map<String, PojoInfo> pojoInfos = new HashMap<>();
    private final Map<String, Attribute> attributes = new HashMap<>();

    private Package aPackage =    new Package("de.fh.kiel.advancedjava.pojomodel.exampleData", "exampleData", null);
    private Package javaLang = new Package("java.lang", "lang", null);

    private static final String PATH_TO_EXAMPLE_DATA ="/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/";

    private static final String PATH_TO_BASE_64_FOLDER = PATH_TO_EXAMPLE_DATA + "base64Encoded/";

    private static final String PATH_TO_JSON_FOLDER = PATH_TO_EXAMPLE_DATA +"json/";

    private static final String PATH_TO_CLASSES =  PATH_TO_EXAMPLE_DATA + "class/";


    public TestingUtil() throws IOException {
        loadClassesEncodedInBase64();
        loadClassesEncodedInJSON();
        loadClassesFromClassFile();
        loadPojos();
        loadEmptyHulls();
        loadPojoInfos();
        loadAttributes();
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
        base64Values.put("exploit", createBase64Path( "exploit.txt"));

    }
    private void loadClassesEncodedInJSON() throws IOException {
        jsonValues.put("badAttributeChangeDTO",createJSONPath( "BadAttributeChangeDTO.json"));
        jsonValues.put("attributeChangeDTO", createJSONPath( "AttributeChangeDTO.json"));
        jsonValues.put("attributeAddDTO", createJSONPath( "AttributeAddDTO.json"));
        jsonValues.put("attributeAddListDTO", createJSONPath( "AttributeAddListDTO.json"));
        jsonValues.put("badAttributeAddDto", createJSONPath( "BadAttributeAddDto.json"));
        jsonValues.put("pojos", createJSONPath( "pojos.json"));
        jsonValues.put("defaultClass", createJSONPath( "defaultClass.json"));
        jsonValues.put("packagesPojos", createJSONPath( "packagesPojos.json"));
        jsonValues.put("emptyHull", createJSONPath( "emptyHull.json"));
        jsonValues.put("defaultClassStats", createJSONPath( "defaultClassStats.json"));
    }

    public void loadPojos(){


        pojos.put(Class.ANOTHER_CLASS_WITH_PRIMITIVES.name, new Pojo());
        pojos.put(Class.CLASS_WITH_CLASSES.name, new Pojo());
        pojos.put(Class.CLASS_WITH_PARENT.name, new Pojo());
        pojos.put(Class.CLASS_WITH_PRIMTIVES.name,  Pojo.builder()
                .completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtives")
                .className("ClassWithPrimtives")
                .aPackage(aPackage)
                .attributes(Stream.of(
                        Attribute.builder().id("de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesis")
                                .name("is")
                                .accessModifier("")
                                .clazz(new Pojo("java.lang.Boolean", "Boolean", javaLang))
                        .build(),
                        Attribute.builder().id("de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesid")
                                .name("id")
                                .accessModifier("")
                                .clazz(new Pojo("java.lang.Integer", "Integer", javaLang))
                        .build()).collect(Collectors.toCollection(HashSet::new)))
                .parentClass(new Pojo("java.lang.Object", "Object", javaLang))
                .emptyHull(false)
                .build());
        pojos.put(Class.CLASS_WITH_PRIMTIVES_AND_ACCESS_MODIERS.name, new Pojo());
        pojos.put(Class.DEFAULT_CLASS.name, Pojo.builder()
                .completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                .className("DefaultClass")
                .aPackage(aPackage)
                .parentClass(new Pojo("java.lang.Object", "Object", new Package("java.lang", "lang", null)))
                .emptyHull(false)
                .attributes(Stream.of(
                        Attribute.builder().id("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClassname")
                                .name("name")
                                .accessModifier("private")
                                .clazz(new Pojo("java.lang.String", "String", javaLang))
                                .build(),
                        Attribute.builder().id("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClassid")
                                .name("id")
                                .accessModifier("private")
                                .clazz(new Pojo("java.lang.Long", "Long", javaLang))
                                .build()).collect(Collectors.toCollection(HashSet::new)))
                .interfaces(Collections.emptySet())
                .build());
        pojos.put(Class.INTERFACES.name, new Pojo());

    }
    public void loadEmptyHulls(){
        var aPackage =    new Package("de.fh.kiel.advancedjava.pojomodel.exampleData", "exampleData", null);
        emptyHulls.put(Class.ANOTHER_CLASS_WITH_PRIMITIVES.name, new Pojo());
        emptyHulls.put(Class.CLASS_WITH_CLASSES.name, new Pojo());
        emptyHulls.put(Class.CLASS_WITH_PARENT.name, new Pojo());
        emptyHulls.put(Class.CLASS_WITH_PRIMTIVES.name, new Pojo());
        emptyHulls.put(Class.CLASS_WITH_PRIMTIVES_AND_ACCESS_MODIERS.name, new Pojo());
        emptyHulls.put(Class.DEFAULT_CLASS.name, new Pojo("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass", "DefaultClass",aPackage  ));
        emptyHulls.put(Class.INTERFACES.name, new Pojo());
    }
    public void loadPojoInfos(){
        pojoInfos.put(Class.DEFAULT_CLASS.name, new PojoInfo("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass","DefaultClass", "de.fh.kiel.advancedjava.pojomodel.exampleData", "java.lang.Object","Object","java.lang",
                Set.of(new AttributeInfo("name", "java.lang.String", "private", "String", "java.lang"),new AttributeInfo("id", "java.lang.Long", "private", "Long", "java.lang"))
                ,Set.of()) );

    }

    public void loadAttributes(){
        attributes.put(AttributeName.DEFAULT_CLASSNAME.name,     Attribute.builder().id("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClassname")
                .name("name")
                .accessModifier("private")
                .clazz(new Pojo("java.lang.String", "String", javaLang))
                .build());
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
    public Pojo getPojo(String name) {
        return pojos.get(name);
    }
    public Pojo getEmptyHull(String name) {
        return emptyHulls.get(name);
    }
    public PojoInfo getPojoInfo(String name) {
        return pojoInfos.get(name);
    }
    public Attribute getAttribute(String name) {
        return attributes.get(name);
    }
    public Object createObjectFromJSON(String json, java.lang.Class clazz) throws JsonProcessingException {
       return new ObjectMapper().readValue(json, clazz);
    }
    public Object createListOrSetFromJSON(String json, TypeReference<?> typeReference) throws JsonProcessingException {
        return new ObjectMapper().readValue(json,typeReference);
    }


}
