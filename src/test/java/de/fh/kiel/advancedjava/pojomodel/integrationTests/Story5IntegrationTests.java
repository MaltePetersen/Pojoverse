package de.fh.kiel.advancedjava.pojomodel.integrationTests;

        import com.fasterxml.jackson.core.type.TypeReference;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
        import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
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
        import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Autowired
    private AttributeRepository attributeRepository;


    public static String loadData(String location) throws IOException {
        return Files.readString(Paths.get(location));
    }
    @AfterEach()
    void deleteAllSavedClasses(){
        pojoRepository.deleteAll();
    }

    @BeforeEach()
    void SetUp(){
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
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
            attributeRepository.deleteAll();
        }


        @Test
        @DisplayName("Then the endpoint should return an 200 ok and all files should be uploaded")
        void allFilesShouldBeUploaded() throws Exception {
          var content = mvc.perform(MockMvcRequestBuilders.post("/pojos/multiple")
                    .content(loadData(pathToJSONFolder + "pojos.json")).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            var objectMapper = new ObjectMapper();
            assertEquals(objectMapper.readValue(content, new TypeReference<List<Pojo>>() {
            }), pojoRepository.findAll());
        }
    }

}

