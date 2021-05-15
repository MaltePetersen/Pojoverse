package de.fh.kiel.advancedjava.pojomodel.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoStatistics;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the developer wants to get the pojo as a java file")
@Nested
public class Story11IntegrationTests {


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
    }

    @BeforeEach()
    void SetUp(){
        pojoRepository.deleteAll();
    }

    @Nested
    @DisplayName("When the developer request a java File")
    class loadPojo {
        @BeforeEach()
        void SetUp() throws Exception {
            attributeRepository.deleteAll();
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(testingUtil.getBase64Value("defaultClass"))
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn();
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok and return a java file")
        void createJavaFile() throws Exception {
            var content =  mvc.perform(MockMvcRequestBuilders.get("/pojo/class/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
                    assertTrue(content.contains("package de.fh.kiel.advancedjava.pojomodel.exampleData;"));
                    assertTrue( content.contains("public class DefaultClass {"));
                    assertTrue(  content.contains("private Long id;"));
                    assertTrue(  content.contains("private String name;"));
                    assertTrue(  content.contains("}"));
        }
    }

}