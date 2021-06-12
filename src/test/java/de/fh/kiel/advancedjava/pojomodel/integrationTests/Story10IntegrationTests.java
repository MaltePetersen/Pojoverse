package de.fh.kiel.advancedjava.pojomodel.integrationTests;

import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoStatisticsDTO;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
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
    private TestingUtil testingUtil;
    @Autowired
    private PojoFacadeService pojoFacadeService;


    @AfterEach()
    void deleteAllSavedClasses() {
        pojoFacadeService.deleteAllRessources();    }

    @BeforeEach()
    void SetUp() {
        pojoFacadeService.deleteAllRessources();
    }

    @Nested
    @DisplayName("When the developer request stats")
    class loadPojo {
        @BeforeEach()
        void SetUp() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(testingUtil.getBase64Value("defaultClass")).contentType(MediaType.TEXT_PLAIN_VALUE));
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void getStats() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.get("/pojo/statistics/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            assertEquals(testingUtil.createObjectFromJSON(content, PojoStatisticsDTO.class), testingUtil.createObjectFromJSON(testingUtil.getJSONValue("defaultClassStats"), PojoStatisticsDTO.class));
        }
    }

}