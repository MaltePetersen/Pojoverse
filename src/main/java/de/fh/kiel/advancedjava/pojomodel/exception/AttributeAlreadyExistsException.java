package de.fh.kiel.advancedjava.pojomodel.exception;

public class AttributeAlreadyExistsException extends RuntimeException {
    public AttributeAlreadyExistsException(String attribute, String pojoCompletePath) {
        super(attribute + " already exists in " + pojoCompletePath);
    }
}
