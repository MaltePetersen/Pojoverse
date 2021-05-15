package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.AttributeChangeDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoEmptyHullDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.AttributeDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExists;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExist;
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
    private final AttributeService attributeService;
    private final PackageService packageService;
    private final AttributeRepository attributeRepository;

    PojoService(PackageService packageService, PojoRepository pojoRepository, ASMFacadeService asmFacadeService, AttributeService attributeService, AttributeRepository attributeRepository, PojoFacadeService pojoFacadeService
    ){
        this.packageService =packageService;
        this.pojoRepository = pojoRepository;
        this.asmFacadeService = asmFacadeService;
        this.attributeService = attributeService;
        this.attributeRepository = attributeRepository;
        this.pojoFacadeService = pojoFacadeService;
    }



     public Pojo readByteCodeAndCreatePojo(byte[] clazz){

           var pojoInfo = this.asmFacadeService.read(clazz);

           return pojoFacadeService.createPojo(pojoInfo);

    }


    private String buildCompletePath(String packageName, String className){
            return packageName + "." + className;
    }

    public Pojo createPojoEmptyHullFromJSON(PojoEmptyHullDTO emptyHull){

        var completePath = buildCompletePath(emptyHull.getPackageName(), emptyHull.getClassName());

        if ( pojoRepository.existsById(completePath) )
            throw new PojoAlreadyExists(completePath);

       return pojoRepository.save(pojoFacadeService.createPojo(completePath, emptyHull.getClassName(),emptyHull.getPackageName()));
    }

    public void deletePojo(String pojoName){
        var pojo = pojoRepository.findById(pojoName).orElseThrow(() -> new PojoDoesNotExist(pojoName));
     if( attributeRepository.findAllByClazz_CompletePath(pojoName).isEmpty() ){
         pojo.setAttributes(Collections.emptySet());
         pojo.setEmptyHull(true);
         pojoRepository.deleteById(pojo.getCompletePath());
         pojoRepository.save(pojo);
     } else
         pojoRepository.deleteById(pojo.getCompletePath());
    }

    public Pojo changeAttribute(AttributeChangeDTO attributeChangeDTO){
        var pojo = pojoRepository.findById(attributeChangeDTO.getClassName()).orElseThrow(() -> new PojoDoesNotExist(attributeChangeDTO.getClassName()));

            var attr =  pojo.getAttributes().stream().filter(attribute-> attribute.getName().equals(attributeChangeDTO.getAttributeName())).findFirst().orElseThrow(() -> new AttributeDoesNotExist(attributeChangeDTO.getAttributeName(), attributeChangeDTO.getClassName()));

                pojo.getAttributes().remove(attr);
                pojoRepository.deleteById(pojo.getCompletePath());
                pojoRepository.save(pojo);
               return pojo;
    }

    public Pojo getPojo(String completePath){
        return pojoRepository.findById(completePath).orElseThrow(() -> new PojoDoesNotExist(completePath));
    }



}


