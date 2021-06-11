package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.AttributeName;
import de.fh.kiel.advancedjava.pojomodel.Class;
import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.exception.NoValidBase64Exception;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PojosServiceTest {

    @Autowired
    TestingUtil testingUtil;

    @Autowired
    PojoRepository pojoRepository;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    PojosService pojosService;

    @BeforeEach
    void setUp() {
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();

    }

    @Test
    void importPojos() {
        pojoRepository.save(testingUtil.getPojo(Class.CLASS_WITH_PRIMTIVES.name));
        attributeRepository.save(testingUtil.getAttribute(AttributeName.DEFAULT_CLASSNAME.name));
        var pojos = Collections.singletonList(testingUtil.getPojo(Class.DEFAULT_CLASS.name));
        var expected = new ArrayList<Pojo>();
        expected.add(pojos.get(0));
        expected.add(pojos.get(0).getParentClass());
        pojos.get(0).getAttributes().forEach((attribute -> expected.add(attribute.getClazz())));
        var actual = pojosService.importPojos(pojos);
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));
    }

    @Test
    void getAllPojos() {
        pojoRepository.save(testingUtil.getPojo(Class.CLASS_WITH_PRIMTIVES.name));
        var expected = pojoRepository.findAll();
        var actual = pojosService.getAllPojos();
        assertEquals(expected, actual);
    }

    @Test
    void extractJar() throws IOException {
        byte[] pojosAsByteCode;

        try {
            pojosAsByteCode = Base64.getDecoder().decode(testingUtil.getBase64Value("jar"));
        } catch (IllegalArgumentException | IOException i) {
            throw new NoValidBase64Exception();
        }
        var actual = pojosService.extractJar(pojosAsByteCode);
        var expected = pojosService.getAllPojos();
        assertEquals(10, actual.size());
        assertEquals(expected, actual);
    }
}
