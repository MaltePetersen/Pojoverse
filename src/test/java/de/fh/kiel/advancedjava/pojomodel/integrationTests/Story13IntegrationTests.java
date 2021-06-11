package de.fh.kiel.advancedjava.pojomodel.integrationTests;


import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given someone tries to insert a bytecode exploit")
@Nested
public class Story13IntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PojoRepository pojoRepository;

    @Autowired
    private TestingUtil testingUtil;

    @AfterEach()
    void deleteAllSavedClasses() {
        this.pojoRepository.deleteAll();
    }

    @BeforeEach()
    void SetUp() {
        pojoRepository.deleteAll();
    }

    @Nested
    @DisplayName("When the exploiter tries to insert malicous code which tries to create a file on our system")
    class mali {
        @BeforeEach()
        void maliciousCode() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post("/pojo")
                    .content(testingUtil.getBase64Value("exploit"))
                    .accept(MediaType.APPLICATION_JSON));
        }

        @Test
        @DisplayName("Then no file should be created")
        void noFileShouldBecreated() throws Exception {
            var folder = new File(".");
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            assertFalse(Arrays.stream(listOfFiles).anyMatch(file -> file.getName().equals("file.txt")));
        }
    }

}

