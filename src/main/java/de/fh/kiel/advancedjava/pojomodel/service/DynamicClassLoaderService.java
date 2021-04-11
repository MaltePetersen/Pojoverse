package de.fh.kiel.advancedjava.pojomodel.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Service
public class DynamicClassLoaderService  {

    public Class<?> loadClass(byte[] clazz){
        return new ClassLoader(){
            public Class<?> defineClass(byte[] b) {
                return defineClass(null, b, 0, b.length);
            }
        }.defineClass(clazz);
    }

    public void getURLClassLoader() throws MalformedURLException {
        var myJar =       new File("/Users/mpetersen/Desktop/pojo-malte/build/libs/pojomodel-0.0.1-SNAPSHOT.jar").toURI().toURL();

        URLClassLoader child = new URLClassLoader(
                new URL[] {myJar},
                this.getClass().getClassLoader()
        );
        System.out.println("got here");

        System.out.println(child);
        System.out.println(Arrays.toString(child.getURLs()));
        System.out.println(child.getName());
        System.out.println(Arrays.toString(child.getDefinedPackages()));
        System.out.println(child.getClass());
        for (Package pack:child.getDefinedPackages()
             ) {
            System.out.println(pack.getName());
        }
    }

}
