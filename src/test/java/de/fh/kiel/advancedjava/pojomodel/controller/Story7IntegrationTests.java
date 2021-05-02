package de.fh.kiel.advancedjava.pojomodel.controller;


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
@DisplayName("Given the developer want to add an empty Hull ")
@Nested
public class Story7IntegrationTests {


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
    @DisplayName("When the developer wants to upload a emptyHullDTO")
    class uploadEmptyHullDto {
        @AfterEach()
        void deleteAllSavedClasses(){
            pojoRepository.deleteAll();
        }

        @BeforeEach()
        void SetUp(){
            pojoRepository.deleteAll();
        }


        @Test
        @DisplayName("Then the endpoint should return an 200 ok and the emptyHll should be created")
        void allFilesShouldBeUploaded() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo/emptyHull")
                    .content(loadData(pathToJSONFolder + "emptyHull.json")).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn();
                    var pojo = pojoRepository.findById("de.fh.test.hello").get();
            assertEquals("hello", pojo.getClassName());
            assertEquals("de.fh.test", pojo.getAPackage().getId());
        }
    }

}

