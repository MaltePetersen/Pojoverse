package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.Class;
import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoStatisticsDTO;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PojoStatisticsDTOTest {

    @Autowired
    PojoStatisticsService pojoStatisticsService;

    @Autowired
    PojoRepository pojoRepository;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    TestingUtil testingUtil;

    @BeforeEach
    void setUp() {
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
        pojoRepository.save(testingUtil.getPojo(Class.DEFAULT_CLASS.name));
    }

    @Test
    void createStats() {
        var expected = new PojoStatisticsDTO("DefaultClass", "exampleData", 2, "Object", Collections.emptySet(), 0, 0, 1, 1);
        var actual = pojoStatisticsService.getStatistics("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass");
        assertEquals(expected, actual);
    }

}
