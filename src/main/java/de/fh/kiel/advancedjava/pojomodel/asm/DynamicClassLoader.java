package de.fh.kiel.advancedjava.pojomodel.asm;

public class DynamicClassLoader extends ClassLoader {
    public Class<?> defineClass(byte[] b) {
        return defineClass(null, b, 0, b.length);
    }
}