package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.Class;
import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.exception.PackageNameNotAllowedException;
import de.fh.kiel.advancedjava.pojomodel.model.Package;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PackageRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PackageServiceTest {


    @Autowired
    TestingUtil testingUtil;
    @Autowired
    AttributeRepository attributeRepository;
    @Autowired
    private PojoRepository pojoRepository;
    @Autowired
    private PackageService packageService;
    @Autowired
    private PackageRepository packageRepository;

    @BeforeEach
    void deletePackages() {
        packageRepository.deleteAll();
    }

    @Test
    void generateSubPackage() {

        var aPackage = new Package("de", "de", new Package("de.fh", "fh", new Package("de.fh.kiel", "kiel", null)));
        packageService.createPackage("de.fh.kiel");

        assertEquals(packageRepository.findById("de").get(), aPackage);
    }

    @Test
    void generatePackage() {
        assertEquals(packageService.createPackage("de"), new Package("de", "de", null));
    }

    @Test
    void generatePackageInvalidinput() {
        assertThrows(PackageNameNotAllowedException.class, () -> packageService.createPackage("de..fh"));
    }

    @Test
    void getAllPojosFromAPackage() {
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
        var expected = Collections.singletonList(pojoRepository.save(testingUtil.getPojo(Class.DEFAULT_CLASS.name)));
        var actual = packageService.getPojos("de.fh.kiel.advancedjava.pojomodel.exampleData");
        assertEquals(expected, actual);
    }
}
