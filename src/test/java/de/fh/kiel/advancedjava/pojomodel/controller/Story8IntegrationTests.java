package de.fh.kiel.advancedjava.pojomodel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fh.kiel.advancedjava.pojomodel.dto.AttributeChangeDTO;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.model.Primitive;
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

    private static String attributeAddDTO;
    private static String defaultClass;

    private static String pathToExampleData ="/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/";

    private static String pathToJSONFolder = pathToExampleData +"json/";

    private static String pathToBase64Folder = pathToExampleData +"base64Encoded/";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PojoRepository pojoRepository;

    public static String loadData(String location) throws IOException {
        return Files.readString(Paths.get(location));
    }

    @BeforeAll()
    static void loadClassesEncodedInBase64() throws IOException {
        attributeAddDTO = loadData(pathToJSONFolder + "AttributeAddDTO.json");
        defaultClass = loadData(pathToBase64Folder + "DefaultClass.txt");
    }

    @AfterEach()
    void deleteAllSavedClasses(){
        this.pojoRepository.deleteAll();
    }

    @BeforeEach()
    void SetUp(){
        pojoRepository.deleteAll();
    }

    @Nested
    @DisplayName("When the developer sends a addPrimitive request")
    class addPrimitive {
        @BeforeEach()
        void SetUp() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(defaultClass)
                    .accept(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Then the endpoint should return an 200 ok")
        public void attributeChange() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/attribute/primitive/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .content(attributeAddDTO).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isOk())
                    .andReturn();
                var att = pojoRepository.findById("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").get().getAttributes().stream().filter((data)-> data.getName().equals("something")).findFirst();
                var prim = (Primitive) att.get();

                assertEquals("int", prim.getPrimitiveDataType().getName());
                assertEquals("private", prim.getAccessModifier());
        }
    }


    @Nested
    @DisplayName("When the developer sends a request but the pojo does not exist")
    class badDeleteAttribute {


        @Test
        @DisplayName("Then the endpoint should return an 500 internal server error")
        public void attributeChange() throws Exception {
           assertThrows(NestedServletException.class, () ->  mvc.perform(MockMvcRequestBuilders.post("/attribute/primitive/de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass")
                    .content(attributeAddDTO).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)).andExpect(status().isInternalServerError())
                    .andReturn());
        }
    }
}
