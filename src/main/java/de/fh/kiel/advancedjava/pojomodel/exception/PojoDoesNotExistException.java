package de.fh.kiel.advancedjava.pojomodel.exception;

public class PojoDoesNotExistException extends RuntimeException {
    public PojoDoesNotExistException(String pojoName) {
        super(pojoName + "does not exist");
    }
}
