package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.asm.ClassReader;
import de.fh.kiel.advancedjava.pojomodel.asm.PojoClassVisitor;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExists;
import de.fh.kiel.advancedjava.pojomodel.model.AttributeInfo;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.model.PojoInfo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 This Wrapper Class wraps all ASM funcionality used.
 That is because the ClassVisitor needs to store variables as member variables to make them accessible.
 This causes side effects if we implement the visitor as a singleton Service.
 A prototype service could have solved this issue but the pojoService would have needed to be a prototype service aswell only
 because of asm.
 Therefore I decided to wrap the logik of the classreader and the visitor in a wrapper which handles all interaction with asm
 instead of letting asm dictate the architecture of the application.
 */

@Service
public class ASMWrapperService {

    PojoRepository pojoRepository;

    public ASMWrapperService(PojoRepository pojoRepository) {
        this.pojoRepository = pojoRepository;
    }

    public PojoInfo read(byte[] clazz){
        ClassReader classReader = new ClassReader(clazz);

        if(pojoDoesNotAlreadyExist(classReader.getCompletePath())) {

            PojoClassVisitor pojoClassVisitor = new PojoClassVisitor();
            classReader.accept(pojoClassVisitor, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
            Set<AttributeInfo> attributesInfos = pojoClassVisitor.getAttributes();
           return new PojoInfo(classReader.getCompletePath(), classReader.getClassName(), classReader.getPackageName(), classReader.getSuperCompletePath(), classReader.getSuperName(), classReader.getSuperPackageName(),attributesInfos,new HashSet<>( Arrays.asList(classReader.getInterfaces())));

        }
        throw new PojoAlreadyExists(classReader.getCompletePath());
    }

    private boolean pojoDoesNotAlreadyExist(String completePath){
        Optional<Pojo> pojo = pojoRepository.findById(completePath);
        return pojo.isEmpty() || pojo.get().isEmptyHull();
    }
}
