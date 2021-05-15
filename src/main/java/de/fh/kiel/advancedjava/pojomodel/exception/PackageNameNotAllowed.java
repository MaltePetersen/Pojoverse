package de.fh.kiel.advancedjava.pojomodel.exception;

public class PackageNameNotAllowed extends RuntimeException {
    public PackageNameNotAllowed(String packageName) {
        super(packageName + "not correctly formatted");
    }
}