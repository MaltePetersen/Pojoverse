package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.AttributeDeleteDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoEmptyHullDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.AttributeDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExists;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class PojoService {


    private final PojoRepository pojoRepository;
    private final PojoFacadeService pojoFacadeService;
    private final ASMFacadeService asmFacadeService;
    private final AttributeRepository attributeRepository;

    PojoService(PojoRepository pojoRepository, ASMFacadeService asmFacadeService, AttributeRepository attributeRepository, PojoFacadeService pojoFacadeService
    ) {
        this.pojoRepository = pojoRepository;
        this.asmFacadeService = asmFacadeService;
        this.attributeRepository = attributeRepository;
        this.pojoFacadeService = pojoFacadeService;
    }

    public Pojo readByteCodeAndCreatePojo(byte[] clazz) {

        var pojoInfo = this.asmFacadeService.read(clazz);

        return pojoFacadeService.createPojo(pojoInfo);
    }

    public Pojo createPojoEmptyHullFromJSON(PojoEmptyHullDTO emptyHull) {

        var completePath = buildCompletePath(emptyHull.getPackageName(), emptyHull.getClassName());

        if (pojoRepository.existsById(completePath))
            throw new PojoAlreadyExists(completePath);

        return pojoFacadeService.createEmptyHull(completePath, emptyHull.getClassName(), emptyHull.getPackageName());
    }

    public void deletePojo(String pojoName) {
        var pojo = getPojo(pojoName);

        if (attributeRepository.findAllByClazz_CompletePath(pojoName).isEmpty()) {
            pojo.setAttributes(Collections.emptySet());
            pojo.setEmptyHull(true);
            pojoRepository.save(pojo);
        } else
            pojoRepository.deleteById(pojo.getCompletePath());
    }

    public Pojo deleteAttribute(AttributeDeleteDTO attributeDeleteDTO) {
        var pojo = pojoRepository.findById(attributeDeleteDTO.getClassName()).orElseThrow(() -> new PojoDoesNotExist(attributeDeleteDTO.getClassName()));

        var attr = pojo.getAttributes().stream().filter(attribute -> attribute.getName().equals(attributeDeleteDTO.getAttributeName())).findFirst().orElseThrow(() -> new AttributeDoesNotExist(attributeDeleteDTO.getAttributeName(), attributeDeleteDTO.getClassName()));

        pojo.getAttributes().remove(attr);

        return pojoRepository.save(pojo);
    }

    public Pojo getPojo(String completePath) {
        return pojoRepository.findById(completePath).orElseThrow(() -> new PojoDoesNotExist(completePath));
    }

    private String buildCompletePath(String packageName, String className) {
        return packageName + "." + className;
    }

}


