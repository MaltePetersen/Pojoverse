package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;

@Service
public class PojosService {
    private final PojoRepository pojoRepository;
    PojosService( PojoRepository pojoRepository){
        this.pojoRepository = pojoRepository;
    }
    public  Class<?>[] extractPojos(byte[] pojosAsJar) throws MalformedURLException, ClassNotFoundException {
        return null;
    }

    public List<Pojo> getAllPojos(){
        return pojoRepository.findAll();
    }
}
