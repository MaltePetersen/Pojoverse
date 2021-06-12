package de.fh.kiel.advancedjava.pojomodel.integrationTests;

import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to export all pojos")
@Nested
public class Story4IntegrationTests {

    @Autowired
    private TestingUtil testingUtil;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PojoFacadeService pojoFacadeService;

    @AfterEach()
    void deleteAllSavedClasses() {
        pojoFacadeService.deleteAllRessources();
    }

    @BeforeEach()
    void SetUp() {
        pojoFacadeService.deleteAllRessources();
    }

    @Nested
    @DisplayName("When he request all Pojos and one or more exists")
    class GetAllPojos {
        @BeforeEach()
        void SetUp() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .contentType(MediaType.TEXT_PLAIN_VALUE)
                    .content(testingUtil.getBase64Value("defaultClass")))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok and a list of all")
        void createPojos() throws Exception {
            assertNotEquals("[]", mvc.perform(MockMvcRequestBuilders.get("/pojos")
                    .contentType(MediaType.TEXT_PLAIN_VALUE))
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
            assertEquals("{\"pojoList\":[],\"packageList\":[]}", mvc.perform(MockMvcRequestBuilders.get("/pojos")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString());

        }
    }


}


