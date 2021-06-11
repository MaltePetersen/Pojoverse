package de.fh.kiel.advancedjava.pojomodel.exception;

public class NoValidBase64Exception extends RuntimeException {
    public NoValidBase64Exception() {
        super("The Base64 you tried to insert is not valid");
    }
}
