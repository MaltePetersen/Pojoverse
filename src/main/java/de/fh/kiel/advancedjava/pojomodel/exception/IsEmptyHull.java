package de.fh.kiel.advancedjava.pojomodel.exception;

public class IsEmptyHull extends RuntimeException {
    public IsEmptyHull(String pojo) {
        super(pojo + " is an empty hull can not be transformed to a java file");
    }
}
