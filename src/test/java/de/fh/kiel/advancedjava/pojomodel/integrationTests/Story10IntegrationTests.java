package de.fh.kiel.advancedjava.pojomodel.integrationTests;

        import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
        import de.fh.kiel.advancedjava.pojomodel.dto.PojoStatistics;
        import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
        import org.junit.jupiter.api.*;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.http.MediaType;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to get the statistics for a pojo")
@Nested
public class Story10IntegrationTests {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private PojoRepository pojoRepository;
    @Autowired
    private TestingUtil testingUtil;



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
                    .content(testingUtil.getBase64Value("defaultClass"))
                    .accept(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void getStats() throws Exception {
          var content =  mvc.perform(MockMvcRequestBuilders.get("/pojo/statistics/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            assertEquals(testingUtil.createObjectFromJSON(content, PojoStatistics.class), testingUtil.createObjectFromJSON(testingUtil.getJSONValue( "defaultClassStats"), PojoStatistics.class));
        }
    }

}