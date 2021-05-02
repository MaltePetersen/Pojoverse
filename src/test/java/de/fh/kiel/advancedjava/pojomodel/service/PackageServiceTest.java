package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.exception.PackageNameNotAllowed;
import de.fh.kiel.advancedjava.pojomodel.model.Package;
import de.fh.kiel.advancedjava.pojomodel.repository.PackageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class PackageServiceTest {


    @Autowired
    private PackageService packageService;
    @Autowired
    private PackageRepository packageRepository;
    @BeforeEach
    void deletePackages(){
        packageRepository.deleteAll();
    }

    @Test
    void generateSubPackage(){

        var aPackage = new Package("de", "de", new Package("de.fh", "fh", new Package("de.fh.kiel", "kiel", null)));
        packageService.createPackage("de.fh.kiel");

        assertEquals(packageRepository.findById("de").get(),aPackage );
    }
    @Test
    void generatePackage(){
        assertEquals(packageService.createPackage("de"), new Package("de", "de", null) );
    }
    @Test
    void generatePackageInvalidinput(){
        assertThrows(PackageNameNotAllowed.class,() -> packageService.createPackage("de..fh"));
    }
}
