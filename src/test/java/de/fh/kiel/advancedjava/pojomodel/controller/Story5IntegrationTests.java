package de.fh.kiel.advancedjava.pojomodel.controller;

        import com.fasterxml.jackson.core.type.TypeReference;
        import com.fasterxml.jackson.databind.ObjectMapper;
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
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer want to upload preexisting POJOs")
@Nested
public class Story5IntegrationTests {


    private static String pathToExampleData ="/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/";

    private static String pathToJSONFolder = pathToExampleData +"json/";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PojoRepository pojoRepository;

    public static String loadData(String location) throws IOException {
        return Files.readString(Paths.get(location));
    }




    @Nested
    @DisplayName("When the developer wants to upload all pojos")
    class uploadPojos {
        @AfterEach()
        void deleteAllSavedClasses(){
            pojoRepository.deleteAll();
        }

        @BeforeEach()
        void SetUp(){
            pojoRepository.deleteAll();
        }


        @Test
        @DisplayName("Then the endpoint should return an 200 ok and all files should be uploaded")
        void allFilesShouldBeUploaded() throws Exception {
           mvc.perform(MockMvcRequestBuilders.post("/pojos/multiple")
                    .content(loadData(pathToJSONFolder + "pojos.json")).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn();
            var objectMapper = new ObjectMapper();

            assertEquals(pojoRepository.findAll(),  objectMapper.readValue(loadData(pathToJSONFolder + "pojos.json"), new TypeReference<List<Pojo>>(){}));
        }
    }

}

