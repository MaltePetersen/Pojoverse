package de.fh.kiel.advancedjava.pojomodel.exception;


public class PojoAlreadyExists extends RuntimeException{
    public PojoAlreadyExists(String pojoName) {
        super(pojoName + " already exists");
    }
}
