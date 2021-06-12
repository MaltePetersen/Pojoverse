package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.AddAttributeDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.AttributeDeleteDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.AttributeAlreadyExistsException;
import de.fh.kiel.advancedjava.pojomodel.exception.AttributeDoesNotExistException;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExistException;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {


    private final PojoRepository pojoRepository;
    private final PojoFacadeService pojoFacadeService;
    private final AttributeRepository attributeRepository;


    AttributeService(PojoRepository pojoRepository, PojoFacadeService pojoFacadeService, AttributeRepository attributeRepository) {
        this.pojoRepository = pojoRepository;
        this.pojoFacadeService = pojoFacadeService;
        this.attributeRepository = attributeRepository;
    }


    public Pojo addAttribute(String pojoId, AddAttributeDTO addAttributeDTO) {
        var pojo = pojoRepository.findById(pojoId).orElseThrow(() -> new PojoDoesNotExistException(pojoId));

        if (attributeRepository.existsById(pojoFacadeService.generateAttributeId(pojoId, addAttributeDTO.getName())))
            throw new AttributeAlreadyExistsException(addAttributeDTO.getName(), pojoId);


        return pojoFacadeService.addAttribute(addAttributeDTO, pojo);

    }

    public Pojo deleteAttribute(AttributeDeleteDTO attributeDeleteDTO) {
        var pojo = pojoRepository.findById(attributeDeleteDTO.getClassName()).orElseThrow(() -> new PojoDoesNotExistException(attributeDeleteDTO.getClassName()));

        var attr = pojo.getAttributes().stream().filter(attribute -> attribute.getName().equals(attributeDeleteDTO.getAttributeName())).findFirst().orElseThrow(() -> new AttributeDoesNotExistException(attributeDeleteDTO.getAttributeName(), attributeDeleteDTO.getClassName()));

        return pojoFacadeService.deleteAttributeFromPojo(pojo, attr);
    }

}
