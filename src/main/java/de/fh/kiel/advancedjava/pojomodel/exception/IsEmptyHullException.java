package de.fh.kiel.advancedjava.pojomodel.exception;

public class IsEmptyHullException extends RuntimeException {
    public IsEmptyHullException(String pojo) {
        super(pojo + " is an empty hull can not be transformed to a java file");
    }
}
