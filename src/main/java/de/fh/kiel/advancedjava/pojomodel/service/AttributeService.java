package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.AddAttributeDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.AttributeAlreadyExists;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.model.*;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Modifier;
import java.util.Optional;

@Service
public class AttributeService {

    private static final String listType = "java.util.List";

    private final PojoRepository pojoRepository;

    AttributeService(PojoRepository pojoRepository){
        this.pojoRepository =pojoRepository;
    }


    public Attribute addAttribute(String pojoId , AddAttributeDTO addAttributeDTO){
            Pojo pojo =  pojoRepository.findById(pojoId).orElseThrow(() -> new PojoDoesNotExist(pojoId));
            boolean attributeAlreadyExistsInPojo  = pojo.getAttributes().stream().anyMatch(attr -> attr.getName().equals(addAttributeDTO.getName()));
           if( attributeAlreadyExistsInPojo)
               throw new AttributeAlreadyExists(addAttributeDTO.getName(), pojoId);


           Pojo pojoOfAttributeType = transformPrimitivesAndFindIfThePojoExists(addAttributeDTO.getType());

        Attribute attribute = Attribute.builder()
                .id(pojoId+addAttributeDTO.getName())
                .name(addAttributeDTO.getName())
                .accessModifier(addAttributeDTO.getVisibility())
                .clazz(pojoOfAttributeType).build();

           if( pojoOfAttributeType.getCompletePath().equals(listType) ){

               Pojo pojoOfGenericType = transformPrimitivesAndFindIfThePojoExists(addAttributeDTO.getGenericType());
                attribute.setGenericType(pojoOfGenericType);
           }

           pojo.getAttributes().add(attribute);

           pojoRepository.save(pojo);

           return attribute;
    }

    private Pojo findPojoOrElseCreateNew(String pojoCompletePath){
        return pojoRepository.findById(pojoCompletePath)
                .orElse(Pojo.builder()
                        .completePath(pojoCompletePath)
                        .className(parseClassName(pojoCompletePath))
                        .packageName(parsePackageName(pojoCompletePath))
                        .build());
    }

    private Pojo transformPrimitivesAndFindIfThePojoExists(String pojoCompletePath){
        String transformedTypes = transformPrimtives(pojoCompletePath);
        return findPojoOrElseCreateNew(transformedTypes);

    }

    public Attribute createAttribute(String name, String dataTypeName, String access, String className, String packageName){
        Pojo dataType = pojoRepository.findById(dataTypeName).orElse(
                Pojo.builder()
                        .completePath(dataTypeName)
                        .className(className)
                        .packageName(packageName)
                        .emptyHull(true)
                        .build()
        );
        return Attribute.builder().name(name).accessModifier(access).clazz(dataType).build();
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

    private String transformPrimtives(String desc){
        return switch (desc) {
            case "boolean" -> "java.lang.Boolean";
            case "byte" -> "java.lang.Byte";
            case "char" -> "java.lang.Character";
            case "double" -> "java.lang.Double";
            case "float" -> "java.lang.Float";
            case "int" -> "java.lang.Integer";
            case "long" -> "java.lang.Long";
            case "short" -> "java.lang.Short";
            default -> desc;
        };
    }



}
