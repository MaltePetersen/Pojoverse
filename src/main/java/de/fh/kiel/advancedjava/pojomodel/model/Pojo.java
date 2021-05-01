package de.fh.kiel.advancedjava.pojomodel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

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
    private String packageName;

    @Relationship(type = "attributes")
    private Set<Attribute> attributes;

    @Relationship(type = "parent")
    private Pojo parentClass;

    @Relationship(type = "interfaces")
    private Set<String> interfaces;

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

}
