package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.asm.ClassReader;
import de.fh.kiel.advancedjava.pojomodel.asm.PojoClassVisitor;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExists;
import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Handler {

    PojoClassVisitor pojoClassVisitor;
    PojoRepository pojoRepository;
    Handler(PojoClassVisitor pojoClassVisitor, PojoRepository pojoRepository){
        this.pojoClassVisitor = pojoClassVisitor;
        this.pojoRepository = pojoRepository;
    }

    public Pojo read(byte[] clazz){
        ClassReader classReader = new ClassReader(clazz);
       boolean pojoDoesNotAlreadyExist = pojoRepository.findById(classReader.getCompletePath()).isEmpty();
        if(pojoDoesNotAlreadyExist){

            classReader.accept(pojoClassVisitor, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);

            Set<Attribute> attributes = setIdOfAttributes(pojoClassVisitor.getAttributes(),classReader.getCompletePath());

            var pojo =   Pojo.builder().completePath(classReader.getCompletePath())
                    .className(classReader.getClassName())
                    .packageName(classReader.getPackageName())
                    .parentClass(
                            Pojo.builder()
                                    .completePath(classReader.getSuperCompletePath())
                                    .className(classReader.getSuperName())
                                    .packageName(classReader.getSuperName())
                                    .emptyHull(true).build()
                    ).interfaces( new HashSet<>( Arrays.asList(classReader.getInterfaces())))
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
}


