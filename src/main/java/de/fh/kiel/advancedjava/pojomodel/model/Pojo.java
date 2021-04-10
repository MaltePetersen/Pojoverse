package de.fh.kiel.advancedjava.pojomodel.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Objects;
import java.util.Set;

@Node
public class Pojo {

    private boolean emptyHull;

    @Id
    private String className;
    private String packageName;

    @Relationship(type = "attributes")
    private Set<Attribute> attributes;

    @Relationship(type = "parent")
    private Pojo parentClass;

    @Relationship(type = "interfaces")
    private Set<String> interfaces;

    public Pojo() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Pojo(String className, String packageName, Set<Attribute> attributes, Pojo parentClass, Set<String> interfaces) {
        this.className = className;
        this.packageName = packageName;
        this.attributes = attributes;
        this.parentClass = parentClass;
        this.interfaces = interfaces;
        this.emptyHull = false;
    }
    public Pojo(String className, String packageName ){
        this.className = className;
        this.packageName = packageName;
        this.emptyHull = true;
    }
    public Pojo(String className, String packageName, Pojo parentClass ){
        this.className = className;
        this.packageName = packageName;
        this.parentClass = parentClass;
        this.emptyHull = true;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Pojo getParentClass() {
        return parentClass;
    }

    public void setParentClass(Pojo parentClass) {
        this.parentClass = parentClass;
    }

    public Set<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Set<String> interfaces) {
        this.interfaces = interfaces;
    }

    public boolean isEmptyHull() {
        return emptyHull;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "emptyHull=" + emptyHull +
                ", className='" + className + '\'' +
                ", packageName='" + packageName + '\'' +
                ", attributes=" + attributes +
                ", parentClass=" + parentClass +
                ", interfaces=" + interfaces +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pojo pojo = (Pojo) o;
        return emptyHull == pojo.emptyHull &&
                Objects.equals(className, pojo.className) &&
                Objects.equals(packageName, pojo.packageName) &&
                Objects.equals(attributes, pojo.attributes) &&
                Objects.equals(parentClass, pojo.parentClass) &&
                Objects.equals(interfaces, pojo.interfaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emptyHull, className, packageName, attributes, parentClass, interfaces);
    }
}
