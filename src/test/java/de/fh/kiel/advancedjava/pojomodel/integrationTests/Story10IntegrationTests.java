package de.fh.kiel.advancedjava.pojomodel.integrationTests;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import de.fh.kiel.advancedjava.pojomodel.dto.PojoStatistics;
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

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to get the statistics for a pojo")
@Nested
public class Story10IntegrationTests {

    private static String defaultClass;

    private static String pathToExampleData ="/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/";

    private static String pathToJSONFolder = pathToExampleData +"json/";

    private static String pathToBase64Folder = pathToExampleData +"base64Encoded/";

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
    @DisplayName("When the developer request stats")
    class loadPojo {
        @BeforeEach()
        void SetUp() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(defaultClass)
                    .accept(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void getStats() throws Exception {
          var content =  mvc.perform(MockMvcRequestBuilders.get("/pojo/statistics/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            var objectMapper = new ObjectMapper();
            assertEquals(objectMapper.readValue(content, PojoStatistics.class), objectMapper.readValue(loadData(pathToJSONFolder + "defaultClassStats.json"), PojoStatistics.class));
        }
    }

}