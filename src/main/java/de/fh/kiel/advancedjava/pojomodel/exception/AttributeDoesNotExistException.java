package de.fh.kiel.advancedjava.pojomodel.exception;

public class AttributeDoesNotExistException extends RuntimeException {
    public AttributeDoesNotExistException(String attribute, String pojo) {
        super(attribute + " does not exist in " + pojo);
    }
}
