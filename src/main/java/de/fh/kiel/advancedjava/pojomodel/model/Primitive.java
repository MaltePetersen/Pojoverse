package de.fh.kiel.advancedjava.pojomodel.model;

public class Primitive extends  Attribute{

    private PrimitiveDataType dataType;

    public Primitive(String name, String dataType, int accessModifier) {
        super(name,  accessModifier);
        this.dataType = new PrimitiveDataType(dataType);
    }

    @Override
    public String getDataType() {
        return dataType.toString();
    }

    public void setDataType(PrimitiveDataType dataType) {
        this.dataType = dataType;
    }
}
