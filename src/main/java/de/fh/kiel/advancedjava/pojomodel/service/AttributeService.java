package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.AddAttributeDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.AttributeAlreadyExists;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.model.*;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AttributeService {

    private static final String LIST_TYPE = "java.util.List";

    private final PojoRepository pojoRepository;
    private final PackageService packageService;
    private final PojoFacadeService pojoFacadeService;


    AttributeService(PojoRepository pojoRepository, PackageService packageService, PojoFacadeService pojoFacadeService){
        this.pojoRepository = pojoRepository;
        this.packageService = packageService;
        this.pojoFacadeService = pojoFacadeService;
    }


    public Attribute addAttribute(String pojoId , AddAttributeDTO addAttributeDTO){
            var pojo =  pojoRepository.findById(pojoId).orElseThrow(() -> new PojoDoesNotExist(pojoId));
            boolean attributeAlreadyExistsInPojo  = pojo.getAttributes() != null && pojo.getAttributes().stream().anyMatch(attr -> attr.getName().equals(addAttributeDTO.getName()));
           if( attributeAlreadyExistsInPojo)
               throw new AttributeAlreadyExists(addAttributeDTO.getName(), pojoId);


           var pojoOfAttributeType = transformPrimitivesAndFindIfThePojoExists(addAttributeDTO.getType());

        var attribute = Attribute.builder()
                .id(pojoId+addAttributeDTO.getName())
                .name(addAttributeDTO.getName())
                .accessModifier(addAttributeDTO.getVisibility())
                .clazz(pojoOfAttributeType).build();

           if( pojoOfAttributeType.getCompletePath().equals(LIST_TYPE) ){

               var pojoOfGenericType = transformPrimitivesAndFindIfThePojoExists(addAttributeDTO.getGenericType());
                attribute.setGenericType(pojoOfGenericType);
           }

           if( pojo.getAttributes() == null )
               pojo.setAttributes(Collections.singleton(attribute));
           else
               pojo.getAttributes().add(attribute);

           pojoRepository.save(pojo);

           return attribute;
    }

    private Pojo transformPrimitivesAndFindIfThePojoExists(String pojoCompletePath){
        String transformedTypes = Primitiv.getWrapperByPrimitive(pojoCompletePath);
        return pojoFacadeService.createPojo(transformedTypes, parseClassName(transformedTypes), parsePackageName(transformedTypes));
    }


    private String parsePackageName(String completePath){
        if(completePath.lastIndexOf(".") != -1)
              return completePath.substring(0, completePath.lastIndexOf("."));
        return completePath;
    }

    private String parseClassName(String completePath) {
    if(completePath.lastIndexOf(".") != -1)
        return completePath.substring(completePath.lastIndexOf(".")+1);
    return completePath;
    }

}
