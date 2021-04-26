package de.fh.kiel.advancedjava.pojomodel.exception;

public class AttributeDoesNotExist extends RuntimeException{
    public AttributeDoesNotExist(String attribute, String pojo) {
        super(attribute + " does not exist in " + "pojo");
    }
}
