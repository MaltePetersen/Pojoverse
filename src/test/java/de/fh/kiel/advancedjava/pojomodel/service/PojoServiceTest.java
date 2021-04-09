package de.fh.kiel.advancedjava.pojomodel.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given the Pojo should be saved in the db")
@Nested
public class PojoServiceTest {

    @Nested
    @DisplayName("When the pojo is new to the system")
    class NewPojo {
        @Test
        @DisplayName("Then it should be saved to the db")
        public void saveToDb() throws Exception {
            fail();
        }
    }

    @Nested
    @DisplayName("When the pojo is not new to the system but only exist as an empty hull")
    class ReplaceEmptyHullWithNewPojo {
        @Test
        @DisplayName("Then the empty hull should be replaced wih the new pojo")
        public void replace() throws Exception {
            fail();
        }
    }
    @Nested
    @DisplayName("When the pojo already Exists")
    class AlreadyExists {
        @Test
        @DisplayName("Then a 'pojo already exist error should be thrown'")
        public void pojoAlreadyExist() throws Exception {
            fail();
        }
    }
}
