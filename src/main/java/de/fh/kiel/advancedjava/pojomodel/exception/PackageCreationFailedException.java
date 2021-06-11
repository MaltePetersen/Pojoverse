package de.fh.kiel.advancedjava.pojomodel.exception;

public class PackageCreationFailedException extends RuntimeException {
    public PackageCreationFailedException(String packageName) {
        super("The creation of the package " + packageName + " failed.");
    }
}
