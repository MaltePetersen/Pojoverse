package de.fh.kiel.advancedjava.pojomodel.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Objects;

@Deprecated
public class PrimitiveDataType {
 @Id
 private String name;

    public PrimitiveDataType(String name) {
        this.name = name;
    }

    public PrimitiveDataType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimitiveDataType that = (PrimitiveDataType) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "PrimitiveDataTypes{" +
                "name='" + name + '\'' +
                '}';
    }
}
