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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer want to add an empty Hull ")
@Nested
public class Story7IntegrationTests {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private PojoFacadeService pojoFacadeService;
    @Autowired
    private PojoRepository pojoRepository;
    @Autowired
    private TestingUtil testingUtil;


    @Nested
    @DisplayName("When the developer wants to upload a emptyHullDTO")
    class uploadEmptyHullDto {
        @AfterEach()
        void deleteAllSavedClasses() {
            pojoFacadeService.deleteAllRessources();
        }

        @BeforeEach()
        void SetUp() {
            pojoFacadeService.deleteAllRessources();
        }


        @Test
        @DisplayName("Then the endpoint should return an 201 created and the emptyHll should be created")
        void allFilesShouldBeUploaded() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo/emptyHull")
                    .content(testingUtil.getJSONValue("emptyHull")).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isCreated())
                    .andReturn();
            var pojo = pojoRepository.findById("de.fh.test.hello").get();
            assertEquals("hello", pojo.getClassName());
            assertEquals("de.fh.test", pojo.getAPackage().getId());
        }
    }

}

