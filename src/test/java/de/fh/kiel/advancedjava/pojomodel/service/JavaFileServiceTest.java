package de.fh.kiel.advancedjava.pojomodel.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.fh.kiel.advancedjava.pojomodel.Class;
import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.dto.AddAttributeDTO;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JavaFileServiceTest {

    @Autowired
    TestingUtil testingUtil;

    @Autowired
    PojoFacadeService pojoFacadeService;

    @Autowired
    PojoRepository pojoRepository;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    JavaFileService javaFileService;

    @BeforeEach
    void setUp() {
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
    }

    @Test
    void createDefaultJavaFile() {
        pojoRepository.save(testingUtil.getPojo(Class.DEFAULT_CLASS.name));
        var actual = javaFileService.createJavaFile("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass");
        assertTrue(actual.contains("package de.fh.kiel.advancedjava.pojomodel.exampleData;"));
        assertFalse(actual.contains("java.lang"));
        assertTrue(actual.contains("public class DefaultClass {"));
        assertTrue(actual.contains("private Long id;"));
        assertTrue(actual.contains("private String name;"));
        assertTrue(actual.contains("}"));
    }

    @Test
    void createInterfaceJavaFile() {
        pojoRepository.save(testingUtil.getPojo(Class.INTERFACES.name));
        var actual = javaFileService.createJavaFile("de.fh.kiel.advancedjava.pojomodel.exampleData.Interfaces");
        assertTrue(actual.contains("package de.fh.kiel.advancedjava.pojomodel.exampleData;"));
        assertTrue(actual.contains("public class Interfaces implements Here,There {"));

    }

    @Test
    void createParentJavaFile() {
        pojoRepository.save(testingUtil.getPojo(Class.CLASS_WITH_PARENT.name));
        var actual = javaFileService.createJavaFile("de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithParents");
        assertTrue(actual.contains("import de.fh.Test;"));
        assertTrue(actual.contains("public class ClassWithParents extends Test {"));

    }

    @Test
    void createFileWithGeneric() throws JsonProcessingException {
        var pojo = deepCopy(testingUtil.getPojo(Class.DEFAULT_CLASS.name));
        pojoFacadeService.addAttribute(new AddAttributeDTO("test", "java.util.List", "public", "fh.test.Example"), pojo);

        var actual = javaFileService.createJavaFile("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass");
        System.out.println(actual);
        assertTrue(actual.contains("import java.util.List;"));
        assertTrue(actual.contains("import fh.test.Example;"));

        assertTrue(actual.contains("public List<Example> test;"));
    }

    Pojo deepCopy(Pojo pojo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(objectMapper.writeValueAsString(pojo), Pojo.class);

    }

}
