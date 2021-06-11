package de.fh.kiel.advancedjava.pojomodel.exception;

public class PackageNameNotAllowedException extends RuntimeException {
    public PackageNameNotAllowedException(String packageName) {
        super(packageName + "not correctly formatted");
    }
}