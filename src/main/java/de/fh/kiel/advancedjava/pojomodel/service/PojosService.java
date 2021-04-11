package de.fh.kiel.advancedjava.pojomodel.service;

import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class PojosService {
    private final DynamicClassLoaderService dynamicClassLoaderService;
    PojosService(DynamicClassLoaderService dynamicClassLoaderService){
        this.dynamicClassLoaderService = dynamicClassLoaderService;
    }
    public  Class<?>[] extractPojos(byte[] pojosAsJar) throws MalformedURLException {
        this.dynamicClassLoaderService.getURLClassLoader();
        return null;
    }
}
