package de.fh.kiel.advancedjava.pojomodel.model;

public class Primitive extends  Attribute{

    private PrimitiveDataType primitiveDataType;

    public Primitive(String name, PrimitiveDataType primitiveDataType, String accessModifier) {
        super(name,  accessModifier);
        this.primitiveDataType = primitiveDataType;
    }

    public PrimitiveDataType getPrimitiveDataType() {
        return primitiveDataType;
    }

    public void setPrimitiveDataType(PrimitiveDataType primitiveDataType) {
        this.primitiveDataType = primitiveDataType;
    }
}
