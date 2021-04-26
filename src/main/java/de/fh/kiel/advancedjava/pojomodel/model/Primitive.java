package de.fh.kiel.advancedjava.pojomodel.model;
@Deprecated
public class Primitive extends  Attribute{

    private PrimitiveDataType primitiveDataType;

    public Primitive(String name, PrimitiveDataType primitiveDataType, String accessModifier) {
        super();
        this.primitiveDataType = primitiveDataType;
    }

    public PrimitiveDataType getPrimitiveDataType() {
        return primitiveDataType;
    }

    public void setPrimitiveDataType(PrimitiveDataType primitiveDataType) {
        this.primitiveDataType = primitiveDataType;
    }
}
