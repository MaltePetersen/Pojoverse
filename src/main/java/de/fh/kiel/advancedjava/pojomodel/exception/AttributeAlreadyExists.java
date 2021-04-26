package de.fh.kiel.advancedjava.pojomodel.exception;

public class AttributeAlreadyExists extends RuntimeException{
    public AttributeAlreadyExists(String attribute, String pojoCompletePath) {
        super(attribute + " already exists in " + pojoCompletePath);
    }
}
