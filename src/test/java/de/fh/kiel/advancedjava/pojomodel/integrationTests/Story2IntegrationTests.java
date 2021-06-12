package de.fh.kiel.advancedjava.pojomodel.integrationTests;

import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import de.fh.kiel.advancedjava.pojomodel.service.PackageService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to delete a pojo")
@Nested
public class Story2IntegrationTests {

    private static String classWithClasses;
    private static String pathToBase64Folder = "/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/base64Encoded/";
    @Autowired
    TestingUtil testingUtil;
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PojoRepository pojoRepository;
    @Autowired
    private PackageService packageService;
    @Autowired
    private PojoFacadeService pojoFacadeService;

    public static String loadData(String location) throws IOException {
        return Files.readString(Paths.get(location));
    }

    @BeforeAll()
    static void loadClassesEncodedInBase64() throws IOException {
        classWithClasses = loadData(pathToBase64Folder + "ClassWithClasses.txt");
    }

    @AfterEach()
    void deleteAllSavedClasses() {
        this.pojoFacadeService.deleteAllRessources();
    }

    @BeforeEach()
    void SetUp() {
        pojoFacadeService.deleteAllRessources();
    }

    @Nested
    @DisplayName("When the class does not exist")
    class DoesNotExist {
        @Test
        @DisplayName("Then the endpoint should return an 400 bad request")
        void deletePojo() throws Exception {
            mvc.perform(MockMvcRequestBuilders.delete("/pojo/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();
        }
    }

    @Nested
    @DisplayName("When the class exist and it does not have any depenpends")
    class ClassExists {
        @BeforeEach()
        void SetUp() {
            pojoRepository.save(Pojo.builder().completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").className("DefaultClass").aPackage(packageService.createPackage("de.fh.kiel.advancedjava.pojomodel.exampleData")).emptyHull(false).build());
        }

        @Test
        @DisplayName("Then the endpoint should return 200 ok")
        void deletePojo() throws Exception {
            mvc.perform(MockMvcRequestBuilders.delete("/pojo/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
        }
    }

    @Nested
    @DisplayName("When the class exist and it does have depenpends")
    class ClassExistsWithDepentens {
        @BeforeEach()
        void SetUp() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(classWithClasses).contentType(MediaType.TEXT_PLAIN_VALUE)
            );
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(testingUtil.getBase64Value("defaultClass")).contentType(MediaType.TEXT_PLAIN_VALUE)
            );
        }

        @Test
        @DisplayName("Then the endpoint should return 200 ok, but should not delete the Pojo, instead it should be converted to an empty hull")
        void deletePojo() throws Exception {
            mvc.perform(MockMvcRequestBuilders.delete("/pojo/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .accept(MediaType.APPLICATION_JSON));
            assertTrue(pojoRepository.findById("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").get().isEmptyHull());
            assertEquals(Collections.emptySet(), pojoRepository.findById("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").get().getAttributes());

        }
    }

}


