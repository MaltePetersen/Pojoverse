package de.fh.kiel.advancedjava.pojomodel.integrationTests;

import com.fasterxml.jackson.core.type.TypeReference;
import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
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

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to get all pojos in a package")
@Nested
public class Story6IntegrationTests {

    @Autowired
    PackageRepository packageRepository;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private PojoRepository pojoRepository;
    @Autowired
    private TestingUtil testingUtil;
    @Autowired
    private AttributeRepository attributeRepository;
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

    void performGetPojo(String pojo) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/pojo")
                .content(pojo)
                .contentType(MediaType.TEXT_PLAIN_VALUE));
    }

    @Nested
    @DisplayName("When the developer sends a request for the content of package in the package only one pojo exists")
    class sendRequest {
        @BeforeEach()
        void SetUp() throws Exception {
            performGetPojo(testingUtil.getBase64Value("defaultClass"));
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void getAll() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.get("/package/de.fh.kiel.advancedjava.pojomodel.exampleData")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            assertEquals(testingUtil.createListOrSetFromJSON(content, new TypeReference<List<Pojo>>() {
            }), testingUtil.createListOrSetFromJSON(testingUtil.getJSONValue("defaultClass"), new TypeReference<List<Pojo>>() {
            }));
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void getAllParentPackage() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.get("/package/de.fh.kiel.advancedjava.pojomodel")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            assertEquals(testingUtil.createListOrSetFromJSON(content, new TypeReference<Set<Pojo>>() {
            }), testingUtil.createListOrSetFromJSON(testingUtil.getJSONValue("defaultClass"), new TypeReference<Set<Pojo>>() {
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
            pojoRepository.deleteAll();
            attributeRepository.deleteAll();
            performGetPojo(testingUtil.getBase64Value("defaultClass"));
            performGetPojo(testingUtil.getBase64Value("classWithPrimtives"));
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void getAll() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.get("/package/de.fh.kiel.advancedjava.pojomodel.exampleData")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            assertEquals((testingUtil.createListOrSetFromJSON(content, new TypeReference<Set<Pojo>>() {
            })), (testingUtil.createListOrSetFromJSON(testingUtil.getJSONValue("packagesPojos"), new TypeReference<Set<Pojo>>() {
            })));
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void getAllParentPackage() throws Exception {
            var content = mvc.perform(MockMvcRequestBuilders.get("/package/de.fh.kiel.advancedjava.pojomodel")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            assertEquals(testingUtil.createListOrSetFromJSON(content, new TypeReference<Set<Pojo>>() {
            }), (testingUtil.createListOrSetFromJSON(testingUtil.getJSONValue("packagesPojos"), new TypeReference<Set<Pojo>>() {
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