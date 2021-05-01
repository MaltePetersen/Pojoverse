package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
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
    private static byte[] classWithPrimtives;
    private static byte[] defaultClass;

    @Autowired
    PojoService pojoService;

    @Autowired
    PojoRepository pojoRepository;

    @AfterEach()
    void deleteAllSavedClasses(){
        this.pojoRepository.deleteAll();
    }

    @BeforeEach()
    void SetUp(){
        pojoRepository.deleteAll();
    }

    public static byte[] loadData(String location) throws IOException {
        return Files.readAllBytes(Paths.get(location));
    }

    @BeforeAll()
    static void loadClasses() throws IOException {
        String pathToCompiledClasses = "/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/class/";
        classWithPrimtives = loadData( pathToCompiledClasses + "ClassWithPrimtives.class");
        defaultClass = loadData(pathToCompiledClasses + "DefaultClass.class");
    }

    @Nested
    @DisplayName("When the pojo is new to the system")
    class NewPojo {
        @Test
        @DisplayName("Then it should be saved to the db")
        void saveToDb() {
            Pojo pojo = pojoService.createPojo(classWithPrimtives);
            assertNotNull(pojo);
            assertTrue(pojoRepository.existsById(pojo.getCompletePath()));
        }
    }

    @Nested
    @DisplayName("When the pojo is not new to the system but only exist as an empty hull")
    class ReplaceEmptyHullWithNewPojo {
        @BeforeEach()
        void SetUp(){
            pojoRepository.save(Pojo.builder().completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").className("DefaultClass").packageName("de.fh.kiel.advancedjava.pojomodel.exampleData").emptyHull(true).build());
        }

        @Test
        @DisplayName("Then the empty hull should be replaced wih the new pojo")
        void replace() throws Exception {
            Pojo pojo = pojoService.createPojo(defaultClass);
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
            pojoRepository.save(Pojo.builder().completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").className("DefaultClass").packageName("de.fh.kiel.advancedjava.pojomodel.exampleData").emptyHull(false).build());
        }
        @Test
        @DisplayName("Then null should be returned")
        void pojoAlreadyExist() throws Exception {
           assertThrows(de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExists.class,() -> pojoService.createPojo(defaultClass));
        }
    }
}
