package de.fh.kiel.advancedjava.pojomodel.model;

import java.util.Arrays;

public enum Primitiv {
    BOOLEAN("boolean", "java.lang.Boolean"),
    BYTE("byte", "java.lang.Byte"),
    CHAR("chr", "java.lang.Character"),
    DOUBLE("double", "java.lang.Double"),
    FLOAT("float", "java.lang.Float"),
    INT("int", "java.lang.Integer"),
    LONG("long", "java.lang.Long"),
    SHORT("short", "java.lang.Short");

    private final String primitive;

    private final String wrapper;

    private Primitiv(String primitive, String wrapper) {
        this.primitive = primitive;
        this.wrapper = wrapper;
    }

    public static String getWrapperByPrimitive(String primitive) {
        return Arrays.stream(Primitiv.values()).filter(p -> p.primitive.equals(primitive)).findFirst().map((p -> p.wrapper)).orElseGet(() -> primitive);
    }
}
