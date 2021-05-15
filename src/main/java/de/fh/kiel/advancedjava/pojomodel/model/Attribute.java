package de.fh.kiel.advancedjava.pojomodel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;


@Node
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attribute {

    @Id
    private String id;
    @Version
    private Long version;
    private String name;
    private String accessModifier;
    //isOnlySet if the class is of type java.util.list
    private Pojo genericType;

    @Relationship("DeclaringClass")
    private Pojo clazz;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var attribute = (Attribute) o;
        return Objects.equals(id, attribute.id) &&
                Objects.equals(name, attribute.name) &&
                Objects.equals(accessModifier, attribute.accessModifier) &&
                Objects.equals(genericType, attribute.genericType) &&
                Objects.equals(clazz, attribute.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, accessModifier, genericType, clazz);
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", accessModifier='" + accessModifier + '\'' +
                ", genericType=" + genericType +
                ", clazz=" + clazz +
                '}';
    }
}
