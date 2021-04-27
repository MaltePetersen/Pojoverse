package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.AttributeChangeDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.AttributeDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import de.fh.kiel.advancedjava.pojomodel.model.AttributeInfo;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.model.PojoInfo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PojoService {

    PojoRepository pojoRepository;
    ASMWrapperService ASMWrapperService;
    AttributeService attributeService;

    PojoService(PojoRepository pojoRepository, ASMWrapperService ASMWrapperService, AttributeService attributeService
    ){
        this.pojoRepository = pojoRepository;
        this.ASMWrapperService = ASMWrapperService;
        this.attributeService = attributeService;
    }

    public Pojo createPojo(byte[] clazz){

           PojoInfo pojoInfo = this.ASMWrapperService.read(clazz);

            Set<Attribute> attributes = constructAttributesFromAttributesInfo(pojoInfo.getAttributes(), pojoInfo.getCompletePath());

            var superClass = getSuperClass(pojoInfo.getParentClassCompletePath(),pojoInfo.getParentClassName(), pojoInfo.getParentClassPackageName());

            var pojo =   Pojo.builder().completePath(pojoInfo.getCompletePath())
                    .className(pojoInfo.getClassName())
                    .packageName(pojoInfo.getPackageName())
                    .parentClass(superClass)
                    .interfaces( pojoInfo.getInterfaces())
                    .attributes(attributes)
                    .emptyHull(false).build();
            pojoRepository.save(pojo);
            return pojo;


    }
    private Set<Attribute> constructAttributesFromAttributesInfo(Set<AttributeInfo> attributeInfos, String completePath){
        Set<Attribute> attributes =   attributeInfos.stream().map(a -> attributeService.createAttribute(a.getName(), a.getDataTypeName(), a.getAccessModifier(), a.getClassName(), a.getPackageName())).collect(Collectors.toSet());
        attributes = setIdOfAttributes(attributes, completePath);
        return attributes;
    }
    private Set<Attribute>   setIdOfAttributes(Set<Attribute> attributes, String completePath){
        return attributes.stream().peek(attribute -> attribute.setId(completePath + "" + attribute.getName())).collect(Collectors.toSet());
    }

    public void deletePojo(String pojoName){
        pojoRepository.findById(pojoName).orElseThrow(() -> new PojoDoesNotExist(pojoName));
        pojoRepository.deleteById(pojoName);
    }
    public Pojo changeAttribute(AttributeChangeDTO attributeChangeDTO){
        Pojo pojo = pojoRepository.findById(attributeChangeDTO.getClassName()).orElseThrow(() -> new PojoDoesNotExist(attributeChangeDTO.getClassName()));

            var attr =  pojo.getAttributes().stream().filter((attribute)-> attribute.getName().equals(attributeChangeDTO.getAttributeName())).findFirst().orElseThrow(() -> new AttributeDoesNotExist(attributeChangeDTO.getAttributeName(), attributeChangeDTO.getClassName()));

                pojo.getAttributes().remove(attr);
                pojoRepository.deleteById(pojo.getCompletePath());
                pojoRepository.save(pojo);
               return pojo;
    }
    private Pojo getSuperClass(String completePath, String className, String packageName){
      return pojoRepository.findById(completePath).orElseGet(() -> Pojo.builder()
                .completePath(completePath)
                .className(className)
                .packageName(packageName)
                .emptyHull(true).build());
    }
}


