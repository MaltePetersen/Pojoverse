package de.fh.kiel.advancedjava.pojomodel.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class PojoAlreadyExists extends RuntimeException{
    public PojoAlreadyExists(String PojoName) {
        super(PojoName + " already exists");
    }
}
