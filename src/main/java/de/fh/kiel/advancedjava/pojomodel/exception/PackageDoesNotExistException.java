package de.fh.kiel.advancedjava.pojomodel.exception;

public class PackageDoesNotExistException extends RuntimeException {
    public PackageDoesNotExistException(String packageName) {
        super(packageName + " does not exist");
    }
}
