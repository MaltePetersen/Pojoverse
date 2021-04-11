package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Service
public class PojosService {
    private final DynamicClassLoaderService dynamicClassLoaderService;
    private final PojoRepository pojoRepository;
    PojosService(DynamicClassLoaderService dynamicClassLoaderService, PojoRepository pojoRepository){
        this.dynamicClassLoaderService = dynamicClassLoaderService;
        this.pojoRepository = pojoRepository;
    }
    public  Class<?>[] extractPojos(byte[] pojosAsJar) throws MalformedURLException, ClassNotFoundException {
        this.dynamicClassLoaderService.getURLClassLoader();
        return null;
    }

    public List<Pojo> getAllPojos(){
        return pojoRepository.findAll();
    }
}
