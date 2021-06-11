package de.fh.kiel.advancedjava.pojomodel.exception;

public class CouldNotReadJarException extends RuntimeException {
    public CouldNotReadJarException() {
        super("The jar you tried to insert is not valid");
    }

}
