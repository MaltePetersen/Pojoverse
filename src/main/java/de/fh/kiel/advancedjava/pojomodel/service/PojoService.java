package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.asm.ClassReader;
import de.fh.kiel.advancedjava.pojomodel.asm.PojoClassVisitor;
import de.fh.kiel.advancedjava.pojomodel.dto.AttributeChangeDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.AttributeDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExists;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PojoService {

    PojoClassVisitor pojoClassVisitor;
    PojoRepository pojoRepository;
    PojoService(PojoClassVisitor pojoClassVisitor, PojoRepository pojoRepository){
        this.pojoClassVisitor = pojoClassVisitor;
        this.pojoRepository = pojoRepository;
    }

    public Pojo createPojo(byte[] clazz){
        ClassReader classReader = new ClassReader(clazz);

       if(pojoDoesNotAlreadyExist(classReader.getCompletePath())){

            classReader.accept(pojoClassVisitor, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);

            Set<Attribute> attributes = setIdOfAttributes(pojoClassVisitor.getAttributes(),classReader.getCompletePath());
            var superClass = getSuperClass(classReader.getSuperCompletePath(),classReader.getSuperName(), classReader.getSuperPackageName());

            var pojo =   Pojo.builder().completePath(classReader.getCompletePath())
                    .className(classReader.getClassName())
                    .packageName(classReader.getPackageName())
                    .parentClass(getSuperClass(classReader.getSuperCompletePath(),classReader.getSuperName(), classReader.getSuperPackageName()))
                    .interfaces( new HashSet<>( Arrays.asList(classReader.getInterfaces())))
                    .attributes(attributes)
                    .emptyHull(false).build();
            pojoRepository.save(pojo);
            return pojo;
        }
        throw new PojoAlreadyExists(classReader.getCompletePath());

    }
    private Set<Attribute>   setIdOfAttributes(Set<Attribute> attributes, String completePath){
        return attributes.stream().peek(attribute -> attribute.setId(completePath + "" + attribute.getName())).collect(Collectors.toSet());
    }
    private boolean pojoDoesNotAlreadyExist(String completePath){
        Optional<Pojo> pojo = pojoRepository.findById(completePath);
        return pojo.isEmpty() || pojo.get().isEmptyHull();
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


