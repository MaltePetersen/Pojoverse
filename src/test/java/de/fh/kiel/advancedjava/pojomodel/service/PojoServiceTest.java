package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the Pojo should be saved in the db")
@Nested
public class PojoServiceTest {

    @Autowired
    PojoService pojoService;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    PojoRepository pojoRepository;
    @Autowired
    private PackageService packageService;
    @Autowired
    private TestingUtil testingUtil;


    @AfterEach()
    void deleteAllSavedClasses(){
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
    }

    @BeforeEach()
    void SetUp(){
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();

    }

    @Nested
    @DisplayName("When the pojo is new to the system")
    class NewPojo {
        @Test
        @DisplayName("Then it should be saved to the db")
        void saveToDb() throws IOException {
            Pojo pojo = pojoService.readByteCodeAndCreatePojo(testingUtil.getClassValue("ClassWithPrimtives"));
            assertNotNull(pojo);
            assertTrue(pojoRepository.existsById(pojo.getCompletePath()));
        }
    }

    @Nested
    @DisplayName("When the pojo is not new to the system but only exist as an empty hull")
    class ReplaceEmptyHullWithNewPojo {
        @BeforeEach()
        void SetUp(){
            pojoRepository.save(Pojo.builder().completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").className("DefaultClass").aPackage(packageService.createPackage("de.fh.kiel.advancedjava.pojomodel.exampleData")).emptyHull(true).build());

        }

        @Test
        @DisplayName("Then the empty hull should be replaced wih the new pojo")
        void replace() throws Exception {
            Pojo pojo = pojoService.readByteCodeAndCreatePojo(testingUtil.getClassValue("DefaultClass"));
            assertNotNull(pojo);
            assertTrue(pojoRepository.existsById(pojo.getCompletePath()));
            assertFalse(pojoRepository.findById(pojo.getCompletePath()).get().isEmptyHull());
        }
    }
    @Nested
    @DisplayName("When the pojo already Exists")
    class AlreadyExists {
        @BeforeEach()
        void SetUp(){
            pojoRepository.save(Pojo.builder().completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").className("DefaultClass").aPackage(packageService.createPackage("de.fh.kiel.advancedjava.pojomodel.exampleData")).emptyHull(false).build());
        }
        @Test
        @DisplayName("Then null should be returned")
        void pojoAlreadyExist() throws Exception {
           assertThrows(de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExists.class,() -> pojoService.readByteCodeAndCreatePojo(testingUtil.getClassValue("DefaultClass")));
        }
    }
}
