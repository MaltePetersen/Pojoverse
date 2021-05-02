package de.fh.kiel.advancedjava.pojomodel.exception;

public class PackageCreationFailed extends RuntimeException{
    public PackageCreationFailed(String packageName){
        super("The creation of the package " + packageName + " failed.");
    }
}
