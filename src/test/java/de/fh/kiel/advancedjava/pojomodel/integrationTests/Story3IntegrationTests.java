package de.fh.kiel.advancedjava.pojomodel.integrationTests;

import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to add multiple Pojo at once as a JAR File")
@Nested
public class Story3IntegrationTests {

    private static String jar;


    private static String pathToBase64Folder = "/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/base64Encoded/";
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PojoFacadeService pojoFacadeService;


    public static String loadData(String location) throws IOException {
        return Files.readString(Paths.get(location));
    }

    @BeforeAll()
    static void loadClassesEncodedInBase64() throws IOException {
        jar = loadData(pathToBase64Folder + "Jar.txt");
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
    @DisplayName("When he sends the jar encoded as base64")
    class DoesNotExist {
        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void createPojos() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojos")
                    .content(jar)
                    .contentType(MediaType.TEXT_PLAIN_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
        }
    }


}


