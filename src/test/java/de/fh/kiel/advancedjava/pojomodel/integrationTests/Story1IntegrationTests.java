package de.fh.kiel.advancedjava.pojomodel.integrationTests;

import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import de.fh.kiel.advancedjava.pojomodel.service.PackageService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to add a pojo to the database")
@Nested
public class Story1IntegrationTests {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private TestingUtil testingUtil;
    @Autowired
    private PojoRepository pojoRepository;
    @Autowired
    private PackageService packageService;
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
    @DisplayName("When we send a new compiled Class in base64 to the endpoint")
    class NewClass {
        @Test
        @DisplayName("Then the endpoint should return 201 created as an answer also with just objects")
        void getPojoDefaultClass() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(testingUtil.getBase64Value("defaultClass"))
                    .contentType(MediaType.TEXT_PLAIN_VALUE))
                    .andExpect(status().isCreated())
                    .andReturn();
        }

        @Test
        @DisplayName("Then the endpoint should return 201 created as an answer also with primitives")
        void getPojoPrimitiveClass() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(testingUtil.getBase64Value("classWithPrimtives"))
                    .contentType(MediaType.TEXT_PLAIN_VALUE))
                    .andExpect(status().isCreated())
                    .andReturn();
        }
    }

    @Nested
    @DisplayName("When class already exist as an empty hull")
    class ClassExistsAsEmptyHull {
        @BeforeEach()
        public void createEmptyHullPojo() throws Exception {
            pojoRepository.save(Pojo.builder().completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").className("DefaultClass").aPackage(packageService.createPackage("de.fh.kiel.advancedjava.pojomodel.exampleData")).emptyHull(true).build());
        }

        @Test
        @DisplayName("Then the endpoint should return 201 created as an answer")
        void getPojo() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(testingUtil.getBase64Value("defaultClass"))
                    .contentType(MediaType.TEXT_PLAIN_VALUE))
                    .andExpect(status().isCreated())
                    .andReturn();
        }

        @Nested
        @DisplayName("When a already existing class is send to to the endpoint in base64 and the exisitng class is not an empty hull")
        class AlreadyExistingClass {
            @BeforeEach()
            public void createPojo() throws Exception {
                pojoRepository.deleteAll();
                pojoRepository.save(Pojo.builder().completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").className("DefaultClass").aPackage(packageService.createPackage("de.fh.kiel.advancedjava.pojomodel.exampleData")).emptyHull(false).build());
            }

            @Test
            @DisplayName("Then the endpoint should return an is internal server error")
            void createTheSamePojoAgain() throws Exception {
                mvc.perform(MockMvcRequestBuilders.post("/pojo")
                        .content(testingUtil.getBase64Value("defaultClass"))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isInternalServerError())
                        .andReturn();
            }
        }

        @Nested
        @DisplayName("When the class is not base64 encoded")
        class InputNotBase64 {
            @Test
            @DisplayName("Then the endpoint should return an 500 internal Server error")
            void getPojo() throws Exception {
                mvc.perform(MockMvcRequestBuilders.post("/pojo")
                        .content(testingUtil.getBase64Value("notBase64EncodedClass"))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isInternalServerError())
                        .andReturn();
            }
        }

    }
}
