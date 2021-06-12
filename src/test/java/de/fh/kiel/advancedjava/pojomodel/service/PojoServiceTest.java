package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.Class;
import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.dto.AttributeDeleteDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoEmptyHullDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExistsException;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Given the Pojo should be saved in the db")
public class PojoServiceTest {

    @Autowired
    PojoService pojoService;
    @Autowired
    AttributeRepository attributeRepository;
    @Autowired
    PojoRepository pojoRepository;
    @Autowired
    private PackageService packageService;
    @Autowired
    private TestingUtil testingUtil;
    @Autowired
    private AttributeService attributeService;
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

    @Test
    @DisplayName("When the pojo is new to the system then it should be saved to the db")
    void saveToDb() throws IOException {
        Pojo pojo = pojoService.readByteCodeAndCreatePojo(testingUtil.getClassValue("ClassWithPrimtives"));
        assertNotNull(pojo);
        assertTrue(pojoRepository.existsById(pojo.getCompletePath()));
    }


    @Test
    @DisplayName("When the pojo is not new to the system but only exist as an empty hull Then the empty hull should be replaced wih the new pojo")
    void replace() throws Exception {
        pojoRepository.save(Pojo.builder().completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").className("DefaultClass").aPackage(packageService.createPackage("de.fh.kiel.advancedjava.pojomodel.exampleData")).emptyHull(true).build());
        Pojo pojo = pojoService.readByteCodeAndCreatePojo(testingUtil.getClassValue("DefaultClass"));
        assertNotNull(pojo);
        assertTrue(pojoRepository.existsById(pojo.getCompletePath()));
        assertFalse(pojoRepository.findById(pojo.getCompletePath()).get().isEmptyHull());
    }


    @Test
    @DisplayName("When the pojo already Exists Then null should be returned")
    void pojoAlreadyExist() throws Exception {
        pojoRepository.save(Pojo.builder().completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").className("DefaultClass").aPackage(packageService.createPackage("de.fh.kiel.advancedjava.pojomodel.exampleData")).emptyHull(false).build());
        assertThrows(PojoAlreadyExistsException.class, () -> pojoService.readByteCodeAndCreatePojo(testingUtil.getClassValue("DefaultClass")));
    }

    @Test
    void deletePojoToEmptyHull() {
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
        pojoRepository.save(testingUtil.getPojo(Class.CLASS_WITH_CLASSES.name));
        pojoRepository.save(testingUtil.getPojo(Class.DEFAULT_CLASS.name));
        pojoService.deletePojo("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass");
        var actual = pojoRepository.findById("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").get();
        assertEquals(Collections.emptySet(), actual.getAttributes());
        assertTrue(actual.isEmptyHull());
    }

    @Test
    void createPojoEmptyHullFromJSON() {
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
        var expected = pojoService.createPojoEmptyHullFromJSON(new PojoEmptyHullDTO("de.fh", "Test"));
        var actual = pojoRepository.findById("de.fh.Test").get();
        assertEquals(expected, actual);
    }

    @Test
    void getPojo() {
        var expected = pojoRepository.save(testingUtil.getPojo(Class.DEFAULT_CLASS.name));
        var actual = pojoService.getPojo("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass");
        assertEquals(expected, actual);
    }

    @Test
    void deleteAttribute() {
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
        pojoRepository.save(testingUtil.getPojo(Class.DEFAULT_CLASS.name));
        assertNotNull(attributeService.deleteAttribute(new AttributeDeleteDTO("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass", "de.fh.kiel.advancedjava.pojomodel.exampleData", "id")));
        var actual = pojoRepository.findById("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").get();
        assertFalse(actual.getAttributes().stream().anyMatch(attribute -> attribute.getName().equals("id")));
    }
}
