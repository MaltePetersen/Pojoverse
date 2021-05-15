package de.fh.kiel.advancedjava.pojomodel.exception;

public class CouldNotReadJar extends RuntimeException{
    public CouldNotReadJar() {
        super("The jar you tried to insert is not valid");
    }

}
