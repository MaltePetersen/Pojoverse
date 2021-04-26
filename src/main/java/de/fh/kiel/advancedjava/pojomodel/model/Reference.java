package de.fh.kiel.advancedjava.pojomodel.model;

import org.springframework.data.neo4j.core.schema.Relationship;
@Deprecated
public class Reference extends Attribute{
    @Relationship("DeclaringClass")
    private Pojo clazz;

    public Reference(String name, String dataType, String accessModifier, Pojo clazz) {
        super();
        this.clazz = clazz;
    }

    public Pojo getClazz() {
        return clazz;
    }

    public void setClazz(Pojo clazz) {
        this.clazz = clazz;
    }
}
