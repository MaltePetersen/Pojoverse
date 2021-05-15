package de.fh.kiel.advancedjava.pojomodel.exception;

public class PojoDoesNotExist extends RuntimeException {
    public PojoDoesNotExist(String pojoName) {
        super(pojoName + "does not exist");
    }
}
