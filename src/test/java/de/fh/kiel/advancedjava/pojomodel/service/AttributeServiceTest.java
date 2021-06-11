package de.fh.kiel.advancedjava.pojomodel.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.fh.kiel.advancedjava.pojomodel.Class;
import de.fh.kiel.advancedjava.pojomodel.TestingUtil;
import de.fh.kiel.advancedjava.pojomodel.dto.AddAttributeDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.AttributeAlreadyExistsException;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExistException;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AttributeServiceTest {
    @Autowired
    private TestingUtil testingUtil;



    @Test
    void pojoDoesNotExist(@Mock PojoRepository pojoRepository, @Mock AttributeRepository attributeRepository, @Mock PojoFacadeService pojoFacadeService){
        var attributeService = new AttributeService(pojoRepository, pojoFacadeService, attributeRepository);
        Mockito.when(pojoRepository.findById(any(String.class))).thenReturn(Optional.empty());
        assertThrows(PojoDoesNotExistException.class, () -> attributeService.addAttribute("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass", new AddAttributeDTO("some", "int", "public", null)));
    }

    @Test
    void attributeAlreadyExists(@Mock PojoRepository pojoRepository, @Mock AttributeRepository attributeRepository, @Mock PojoFacadeService pojoFacadeService){
        var attributeService = new AttributeService(pojoRepository, pojoFacadeService, attributeRepository);
        Mockito.when(pojoRepository.findById(any(String.class))).thenReturn(Optional.of(testingUtil.getPojo(Class.DEFAULT_CLASS.name)));
        Mockito.when(attributeRepository.existsById(any(String.class))).thenReturn(true);
        Mockito.when(pojoFacadeService.generateAttributeId(any(), any())).thenReturn("test");
        assertThrows(AttributeAlreadyExistsException.class, () -> attributeService.addAttribute("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass", new AddAttributeDTO("some", "int", "public", null)));
    }
    @Test
    void addAttribute(@Mock PojoRepository pojoRepository, @Mock AttributeRepository attributeRepository, @Mock PojoFacadeService pojoFacadeService) throws JsonProcessingException {
        var attributeService = new AttributeService(pojoRepository, pojoFacadeService, attributeRepository);
        Mockito.when(pojoRepository.findById(any(String.class))).thenReturn(Optional.of(testingUtil.getPojo(Class.DEFAULT_CLASS.name)));
        Mockito.when(attributeRepository.existsById(any(String.class))).thenReturn(false);
        Mockito.when(pojoFacadeService.generateAttributeId(any(), any())).thenReturn("test");
        var pojo = deepCopy( testingUtil.getPojo(Class.DEFAULT_CLASS.name));
        pojo.getAttributes().add(Attribute.builder().id("test").name( "some").build());
        Mockito.when(pojoFacadeService.addAttribute(any(), any())).thenReturn(pojo);

        var actual = attributeService.addAttribute("de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass", new AddAttributeDTO("some", "int", "public", null));
        assertTrue(actual.getAttributes().stream().anyMatch(attribute -> attribute.getName().equals("some")));
    }

    Pojo deepCopy(Pojo pojo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return  objectMapper.readValue(objectMapper.writeValueAsString(pojo), Pojo.class);

    }
}
