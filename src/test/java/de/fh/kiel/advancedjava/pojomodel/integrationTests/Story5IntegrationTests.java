package de.fh.kiel.advancedjava.pojomodel.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.dto.ExportDTO;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PackageRepository;
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
@DisplayName("Given the developer want to upload preexisting POJOs")
@Nested
public class Story5IntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PojoRepository pojoRepository;

    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private TestingUtil testingUtil;

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
    @DisplayName("When the developer wants to upload all pojos")
    class uploadPojos {
        @AfterEach()
        void deleteAllSavedClasses() {
            pojoRepository.deleteAll();
        }

        @BeforeEach()
        void SetUp() {
            pojoRepository.deleteAll();
            attributeRepository.deleteAll();
        }


        @Test
        @DisplayName("Then the endpoint should return an 201 created and all files should be uploaded")
        void allFilesShouldBeUploaded() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.post("/pojos/multiple")
                    .content(testingUtil.getJSONValue("pojos")).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
            var objectMapper = new ObjectMapper();
            assertEquals(objectMapper.readValue(content, ExportDTO.class), new ExportDTO(pojoRepository.findAll(), packageRepository.findAll()));
        }
    }

}

