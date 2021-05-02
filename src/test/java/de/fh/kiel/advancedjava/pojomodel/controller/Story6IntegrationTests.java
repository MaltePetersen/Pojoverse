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
        import java.util.HashSet;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to get all pojos in a package")
@Nested
public class Story6IntegrationTests {


    private static String defaultClass;
    private static String classWithPrimtives;

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
        classWithPrimtives = loadData( pathToBase64Folder + "ClassWithPrimtives.txt");
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
    @DisplayName("When the developer sends a request for the content of package in the package only one pojo exists")
    class sendRequest {
        @BeforeEach()
        void SetUp() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(defaultClass)
                    .accept(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void getAll() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.get("/package/de.fh.kiel.advancedjava.pojomodel.exampleData")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            var objectMapper = new ObjectMapper();
            assertEquals(objectMapper.readValue(content, new TypeReference<List<Pojo>>() {
            }), objectMapper.readValue(loadData(pathToJSONFolder + "defaultClass.json"), new TypeReference<List<Pojo>>() {
            }));
        }
        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void getAllParentPackage() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.get("/package/de.fh.kiel.advancedjava.pojomodel")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            var objectMapper = new ObjectMapper();
            assertEquals(objectMapper.readValue(content, new TypeReference<List<Pojo>>() {
            }), objectMapper.readValue(loadData(pathToJSONFolder + "defaultClass.json"), new TypeReference<List<Pojo>>() {
            }));
        }
        @Test
        @DisplayName("Then the endpoint should return an excetion")
        void throwException() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.get("/package/de.fh.kiel.advancedjavas")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isBadRequest())
                    .andReturn().getResponse().getContentAsString().contains("\"message\":\"de.fh.kiel.advancedjavas does not exist\"");
        }
    }
    @Nested
    @DisplayName("When the developer sends a request for the content of package in the package only one pojo exists")
    class sendRequests {
        @BeforeEach()
        void SetUp() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(defaultClass)
                    .accept(MediaType.APPLICATION_JSON));
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(classWithPrimtives)
                    .accept(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void getAll() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.get("/package/de.fh.kiel.advancedjava.pojomodel.exampleData")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            var objectMapper = new ObjectMapper();
            assertEquals(new HashSet<>(objectMapper.readValue(content, new TypeReference<List<Pojo>>() {
            })), new HashSet<>(objectMapper.readValue(loadData(pathToJSONFolder + "packagesPojos.json"), new TypeReference<List<Pojo>>() {
            })));        }
        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void getAllParentPackage() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.get("/package/de.fh.kiel.advancedjava.pojomodel")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            var objectMapper = new ObjectMapper();
            assertEquals(new HashSet<>(objectMapper.readValue(content, new TypeReference<List<Pojo>>() {
            })), new HashSet<>(objectMapper.readValue(loadData(pathToJSONFolder + "packagesPojos.json"), new TypeReference<List<Pojo>>() {
            })));
        }
        @Test
        @DisplayName("Then the endpoint should return an excetion")
        void throwException() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.get("/package/de.fh.kiel.advancedjavas")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isBadRequest())
                    .andReturn().getResponse().getContentAsString().contains("\"message\":\"de.fh.kiel.advancedjavas does not exist\"");
        }
    }

}