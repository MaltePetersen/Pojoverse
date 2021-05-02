package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PojosService {
    private final PojoRepository pojoRepository;

    private final AttributeRepository attributeRepository;

    PojosService( PojoRepository pojoRepository, AttributeRepository attributeRepository ){
        this.pojoRepository = pojoRepository;
        this.attributeRepository = attributeRepository;
    }
    public  List<Class<?>> extractPojos(byte[] pojosAsJar)  {
        return Collections.emptyList();
    }

    public List<Pojo> getAllPojos(){
        return pojoRepository.findAll();
    }

    public List<Pojo> importPojos(List<Pojo> pojos){
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();

        pojoRepository.saveAll(pojos);
        return pojoRepository.findAll();
    }
}
