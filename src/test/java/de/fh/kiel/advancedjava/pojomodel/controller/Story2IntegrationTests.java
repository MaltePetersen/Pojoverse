package de.fh.kiel.advancedjava.pojomodel.controller;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to delete a pojo")
@Nested
public class Story2IntegrationTests {

    private static String classWithClasses;


    private static String pathToBase64Folder ="/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/base64Encoded/";
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PojoRepository pojoRepository;

    public static String loadData(String location) throws IOException {
        return Files.readString(Paths.get(location));
    }

    @BeforeAll()
    static void loadClassesEncodedInBase64() throws IOException {
        classWithClasses = loadData(pathToBase64Folder + "ClassWithClasses.txt");
    }

    @AfterEach()
    void deleteAllSavedClasses(){
        this.pojoRepository.deleteAll();
    }

    @BeforeEach()
    void SetUp(){
        pojoRepository.deleteAll();
    }

        @Nested
        @DisplayName("When the class does not exist")
        class DoesNotExist {
            @Test
            @DisplayName("Then the endpoint should return an 500 internal Server error")
            public void deletePojo() throws Exception {
                mvc.perform(MockMvcRequestBuilders.delete("/pojo/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isInternalServerError())
                        .andReturn();
            }
        }
    @Nested
    @DisplayName("When the class exist and it does not have any depenpends")
    class ClassExists {
        @BeforeEach()
        void SetUp(){
            pojoRepository.save(new Pojo("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass", "de.fh.kiel.advancedjava.pojomodel.exampleData", null, null, null));
        }
        @Test
        @DisplayName("Then the endpoint should return 200 ok")
        public void deletePojo() throws Exception {
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
                    .content(classWithClasses)
                    .accept(MediaType.APPLICATION_JSON));
        }
        @Test
        @DisplayName("Then the endpoint should return 200 ok, but should not delete the Pojo, instead it should be converted to an empty hull")
        public void deletePojo() throws Exception {
            mvc.perform(MockMvcRequestBuilders.delete("/pojo/de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClasses")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
           assertTrue( pojoRepository.findById("de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClasses").get().isEmptyHull());
        }
    }

    }


