package de.fh.kiel.advancedjava.pojomodel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Node
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pojo {

    private boolean emptyHull;

    @Id
    private String completePath;
    private String className;
    private Package aPackage;
    @Version
    private Long version;

    @Relationship(type = "attributes")
    private Set<Attribute> attributes = Collections.emptySet();

    @Relationship(type = "parent")
    private Pojo parentClass;

    @Relationship(type = "interfaces")
    private Set<String> interfaces = Collections.emptySet();


    public Pojo(String completePath, String className, Package aPackage) {
        this.emptyHull = true;
        this.completePath = completePath;
        this.className = className;
        this.aPackage = aPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var pojo = (Pojo) o;
        return emptyHull == pojo.emptyHull &&
                Objects.equals(completePath, pojo.completePath) &&
                Objects.equals(className, pojo.className) &&
                Objects.equals(aPackage, pojo.aPackage) &&
                Objects.equals(attributes, pojo.attributes) &&
                Objects.equals(parentClass, pojo.parentClass) &&
                Objects.equals(interfaces, pojo.interfaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emptyHull, completePath, className, aPackage, attributes, parentClass, interfaces);
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "emptyHull=" + emptyHull +
                ", completePath='" + completePath + '\'' +
                ", className='" + className + '\'' +
                ", aPackage=" + aPackage +
                ", attributes=" + attributes +
                ", parentClass=" + parentClass +
                ", interfaces=" + interfaces +
                '}';
    }
}
