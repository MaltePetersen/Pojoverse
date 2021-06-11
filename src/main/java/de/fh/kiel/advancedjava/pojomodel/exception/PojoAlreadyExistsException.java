package de.fh.kiel.advancedjava.pojomodel.exception;


public class PojoAlreadyExistsException extends RuntimeException {
    public PojoAlreadyExistsException(String pojoName) {
        super(pojoName + " already exists");
    }
}
