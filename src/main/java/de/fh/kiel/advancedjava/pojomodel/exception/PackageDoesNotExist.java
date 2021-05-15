package de.fh.kiel.advancedjava.pojomodel.exception;

public class PackageDoesNotExist extends RuntimeException {
    public PackageDoesNotExist(String packageName) {
        super(packageName + " does not exist");
    }
}
