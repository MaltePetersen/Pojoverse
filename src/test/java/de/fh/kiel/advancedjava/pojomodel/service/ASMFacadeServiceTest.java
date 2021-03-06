package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExistsException;
import de.fh.kiel.advancedjava.pojomodel.model.AttributeInfo;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.model.PojoInfo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ASMFacadeServiceTest {

    private ASMFacadeService asmFacadeService;

    @Autowired
    private TestingUtil testingUtil;


    @Test
    void readPojoByByteCode(@Mock PojoRepository pojoRepository) throws IOException {
        asmFacadeService = new ASMFacadeService(pojoRepository);

        Mockito.when(pojoRepository.findById(any(String.class))).thenReturn(Optional.empty());
        var attributes = new HashSet<>(Set.of(new AttributeInfo("name", "java.lang.String", "private", "String", "java.lang"), new AttributeInfo("id", "java.lang.Long", "private", "Long", "java.lang")));
        var expected = new PojoInfo("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass", "DefaultClass", "de.fh.kiel.advancedjava.pojomodel.exampleData", "java.lang.Object", "Object", "java.lang",
                attributes, Set.of());

        var actual = asmFacadeService.read(testingUtil.getClassValue("DefaultClass"));
        assertEquals(expected, actual);

    }

    @Test
    void throwPojoAlreadyExists(@Mock PojoRepository pojoRepository) throws IOException {
        asmFacadeService = new ASMFacadeService(pojoRepository);
        Mockito.when(pojoRepository.findById(any(String.class))).thenReturn(Optional.of(Pojo.builder().completePath("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass").build()));


        assertThrows(PojoAlreadyExistsException.class, () -> asmFacadeService.read(testingUtil.getClassValue("DefaultClass")));


    }
}
