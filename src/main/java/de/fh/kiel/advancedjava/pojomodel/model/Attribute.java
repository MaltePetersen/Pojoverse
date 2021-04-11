package de.fh.kiel.advancedjava.pojomodel.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.lang.reflect.Modifier;

@Node
public abstract class Attribute {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String dataType;
    private String accessModifier;
    private Attribute() {
        // Empty constructor required as of Neo4j API 2.0.5
    }


    public Attribute(String name, String accessModifier) {
        this.name = name;
        this.accessModifier = accessModifier;
    }

    public Attribute(String name, String dataType,String accessModifier ) {
        this(name, accessModifier);
        this.dataType = dataType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }
}
