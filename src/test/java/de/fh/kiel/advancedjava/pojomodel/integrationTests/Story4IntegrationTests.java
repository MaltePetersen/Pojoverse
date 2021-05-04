package de.fh.kiel.advancedjava.pojomodel.integrationTests;

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
@DisplayName("Given the developer wants export all pojos")
@Nested
public class Story4IntegrationTests {

    private static String defaultClass;


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
        defaultClass = loadData(pathToBase64Folder + "DefaultClass.txt");
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
    @DisplayName("When he request all Pojos and one or more exists")
    class GetAllPojos {
        @BeforeEach()
        void SetUp() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(defaultClass)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok and a list of all")
        void createPojos() throws Exception {
            assertNotEquals("[]",mvc.perform(MockMvcRequestBuilders.get("/pojos")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString());

        }
    }
    @Nested
    @DisplayName("When he request but none exist")
    class GetAllPojosNoExist {

        @Test
        @DisplayName("Then the endpoint should return an 200 ok and []")
        void createPojos() throws Exception {
            assertEquals("[]",mvc.perform(MockMvcRequestBuilders.get("/pojos")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString());

        }
    }


}


