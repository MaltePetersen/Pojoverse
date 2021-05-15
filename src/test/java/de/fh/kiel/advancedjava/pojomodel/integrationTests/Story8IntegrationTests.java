package de.fh.kiel.advancedjava.pojomodel.integrationTests;

import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to add an attribute of a file")
@Nested
public class Story8IntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PojoRepository pojoRepository;


    @Autowired
    private TestingUtil testingUtil;
    @Autowired
    private AttributeRepository attributeRepository;


    @AfterEach()
    void deleteAllSavedClasses(){
        this.pojoRepository.deleteAll();
        attributeRepository.deleteAll();
    }

    @BeforeEach()
    void SetUp(){
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
    }

    @Nested
    @DisplayName("When the developer sends a addPrimitive request")
    class addPrimitive {
        @BeforeEach()
        void SetUp() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(testingUtil.getBase64Value("defaultClass"))
                    .accept(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        void attributeChange() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/attribute/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .content(testingUtil.getJSONValue("attributeAddDTO")).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn();
                var att = pojoRepository.findById("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").get().getAttributes().stream().filter((data)-> data.getName().equals("something")).findFirst();
                 att.get();

                assertEquals("java.lang.Integer", att.get().getClazz().getCompletePath());
                assertEquals("private", att.get().getAccessModifier());
        }
    }


    @Nested
    @DisplayName("When the developer sends a request but the pojo does not exist")
    class badDeleteAttribute {


        @Test
        @DisplayName("Then the endpoint should return an 200 bad request")
        void attributeChange() throws Exception {
             mvc.perform(MockMvcRequestBuilders.post("/attribute/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .content(testingUtil.getJSONValue("attributeAddDTO")).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isBadRequest())
                    .andReturn();
        }
    }


    @Nested
    @DisplayName("When the developer sends a addAttribute request but the attribute which should be changed already exists")
    class addAttribute {
        @BeforeEach()
        void SetUp() throws Exception {
            pojoRepository.deleteAll();
            attributeRepository.deleteAll();
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(testingUtil.getBase64Value("defaultClass"))
                    .accept(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Then the endpoint should return a bad request")
        void attributeChange() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/attribute/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .content(testingUtil.getJSONValue("badAttributeAddDto")).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isBadRequest())
                    .andReturn();
        }
    }
}
