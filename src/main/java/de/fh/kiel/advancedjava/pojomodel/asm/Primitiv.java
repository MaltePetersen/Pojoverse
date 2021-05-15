package de.fh.kiel.advancedjava.pojomodel.asm;

import java.util.Arrays;

public enum Primitiv {

    BOOLEAN("Z", "Ljava.lang.Boolean;"),
    BYTE("B", "Ljava.lang.Byte;"),
    CHAR("C", "Ljava.lang.Character;"),
    DOUBLE("D", "Ljava.lang.Double;"),
    FLOAT("F", "Ljava.lang.Float;"),
    INTEGER("I", "Ljava.lang.Integer;"),
    LONG("J", "Ljava.lang.Long;"),
    SHORT("S", "Ljava.lang.Short;");


    public final String shortCut;
    public final String wrapper;

    private Primitiv(String shortCut, String wrapper) {
        this.shortCut = shortCut;
        this.wrapper = wrapper;
    }


    public static String getWrapperByPrimitive(String primitive) {
        return Arrays.stream(Primitiv.values()).filter(p -> p.shortCut.equals(primitive)).findFirst().map((p -> p.wrapper)).orElseGet(() -> primitive);
    }
}
