package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.exception.PackageNameNotAllowed;
import de.fh.kiel.advancedjava.pojomodel.model.Package;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class PackageServiceTest {


    @Autowired
    private PackageService packageService;


    @Test
    void test(){
        var aPackage = new Package("de", "de", new Package("de.fh", "fh", new Package("de.fh.kiel", "kiel", null)));

        assertEquals(packageService.createPackage("de.fh.kiel"),aPackage );
        assertThrows(PackageNameNotAllowed.class,() -> packageService.createPackage("de..fh"));
        assertEquals(packageService.createPackage("de"), new Package("de", "de", null) );
    }
}
