package de.fh.kiel.advancedjava.pojomodel;


public enum Class {
    ANOTHER_CLASS_WITH_PRIMITIVES("AnotherClassWithPrimitives"),
    CLASS_WITH_CLASSES("ClassWithClasses"),
    CLASS_WITH_PARENT("ClassWithParent"),
    CLASS_WITH_PRIMTIVES("ClassWithPrimtives"),
    CLASS_WITH_PRIMTIVES_AND_ACCESS_MODIERS("ClassWithPrimtivesAndAccessModiers"),
    DEFAULT_CLASS("DefaultClass"),
    INTERFACES("Interfaces");

    public final String name;

    private Class(String name) {
        this.name = name;
    }
}
