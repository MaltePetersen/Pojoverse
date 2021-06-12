package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.Attribute;
import de.fh.kiel.advancedjava.pojomodel.Class;
import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.dto.ExportDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.NoValidBase64Exception;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PackageRepository;
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
    PackageRepository packageRepository;
    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    PojoService pojoService;
    @Autowired
    private PojoFacadeService pojoFacadeService;

    @BeforeEach
    void setUp() {
        pojoFacadeService.deleteAllRessources();
    }

    @Test
    void importPojos() {
        pojoRepository.save(testingUtil.getPojo(Class.CLASS_WITH_PRIMTIVES.name));
        attributeRepository.save(testingUtil.getAttribute(Attribute.DEFAULT_CLASSNAME.name));
        var pojos = new ExportDTO(Collections.singletonList(testingUtil.getPojo(Class.DEFAULT_CLASS.name)), Collections.emptyList());
        var expected = new ArrayList<Pojo>();
        expected.add(pojos.getPojoList().get(0));
        expected.add(pojos.getPojoList().get(0).getParentClass());
        pojos.getPojoList().get(0).getAttributes().forEach((attribute -> expected.add(attribute.getClazz())));
        var actual = pojoService.importPojos(pojos);
        assertEquals(expected.size(), actual.getPojoList().size());
        assertTrue(expected.containsAll(actual.getPojoList()));
        assertTrue(actual.getPojoList().containsAll(expected));
    }

    @Test
    void getAllPojos() {
        pojoRepository.save(testingUtil.getPojo(Class.CLASS_WITH_PRIMTIVES.name));
        var expected = new ExportDTO(pojoRepository.findAll(), packageRepository.findAll());
        var actual = pojoService.getAllPojos();
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
        var actual = pojoService.savePojos(pojosAsByteCode);
        var expected = pojoService.getAllPojos().getPojoList();
        assertEquals(10, actual.size());
        assertEquals(expected, actual);
    }
}
