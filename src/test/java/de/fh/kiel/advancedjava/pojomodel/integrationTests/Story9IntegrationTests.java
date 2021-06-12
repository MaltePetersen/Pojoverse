package de.fh.kiel.advancedjava.pojomodel.integrationTests;

import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to remove an attribute of a file")
@Nested
public class Story9IntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PojoRepository pojoRepository;

    @Autowired
    private PojoFacadeService pojoFacadeService;

    @Autowired
    private TestingUtil testingUtil;

    @AfterEach()
    void deleteAllSavedClasses() {
        pojoFacadeService.deleteAllRessources();
    }

    @BeforeEach()
    void SetUp() {
        pojoFacadeService.deleteAllRessources();
    }

    @Nested
    @DisplayName("When the developer sends a deleteAttrbute request")
    class deleteAttribute {
        @BeforeEach()
        void SetUp() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(testingUtil.getBase64Value("defaultClass"))
                    .contentType(MediaType.TEXT_PLAIN_VALUE));

        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void attributeChange() throws Exception {
            mvc.perform(MockMvcRequestBuilders.put("/attribute")
                    .content(testingUtil.getJSONValue("attributeChangeDTO")).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn();
            assertFalse(pojoRepository.findById("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").get().getAttributes().stream().anyMatch((data) -> data.getName().equals("name")));
        }
    }

    @Nested
    @DisplayName("When the developer sends a bad deleteAttrbute request")
    class badDeleteAttribute {
        @BeforeEach()
        void SetUp() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(testingUtil.getBase64Value("defaultClass"))
                    .accept(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Then the endpoint should return an 500 internal server error")
        void attributeChange() throws Exception {
            mvc.perform(MockMvcRequestBuilders.put("/pojo")
                    .content(testingUtil.getJSONValue("badAttributeChangeDTO"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isBadRequest())
                    .andReturn();
        }
    }
}
