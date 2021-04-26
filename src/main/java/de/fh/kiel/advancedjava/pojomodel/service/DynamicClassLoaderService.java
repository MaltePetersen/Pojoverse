package de.fh.kiel.advancedjava.pojomodel.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

@Service
public class DynamicClassLoaderService  {

    public Class<?> loadClass(byte[] clazz){
        return new ClassLoader(){
            public Class<?> defineClass(byte[] b) {
                return defineClass(null, b, 0, b.length);
            }
        }.defineClass(clazz);
    }

        //TODO https://stackoverflow.com/questions/1529611/how-to-write-a-java-program-which-can-extract-a-jar-file-and-store-its-data-in-s
    //https://coderecipes.blog/2018/12/08/unzipping-a-jar-file-programmatically/
    public void getURLClassLoader() throws MalformedURLException, ClassNotFoundException {
        var myJar =       new File("/Users/mpetersen/Desktop/pojo-malte/build/libs/pojomodel-0.0.1-SNAPSHOT.jar").toURI().toURL();

        URLClassLoader child = new URLClassLoader(
                new URL[] {myJar},
                this.getClass().getClassLoader()
        );
        System.out.println("got here");
       // System.out.println(child.loadClass("ClassWithPrimtives.class"));
       // System.out.println(child.loadClass("ClassWithPrimtives"));
        //System.out.println(child.loadClass("de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtives.class"));
        System.out.println(child.loadClass("de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtives"));

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
