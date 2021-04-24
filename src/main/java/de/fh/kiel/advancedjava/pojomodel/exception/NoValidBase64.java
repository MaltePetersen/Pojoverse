package de.fh.kiel.advancedjava.pojomodel.exception;

public class NoValidBase64 extends RuntimeException{
    public NoValidBase64() {
        super("The Base64 you tried to insert is not valid");
    }
}
