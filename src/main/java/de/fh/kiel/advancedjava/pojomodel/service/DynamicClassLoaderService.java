package de.fh.kiel.advancedjava.pojomodel.service;

import org.springframework.stereotype.Service;

@Service
public class DynamicClassLoaderService  {

    public Class<?> loadClass(byte[] clazz){
        return new ClassLoader(){
            public Class<?> defineClass(byte[] b) {
                return defineClass(null, b, 0, b.length);
            }
        }.defineClass(clazz);
    }

}
