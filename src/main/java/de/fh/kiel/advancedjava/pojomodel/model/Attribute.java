package de.fh.kiel.advancedjava.pojomodel.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Attribute {
    @Id
    @GeneratedValue
    private Long id;

    //TODO zu enums, alle DataTypes sind bekannt, kann auch ein Pojo sein, hier muss man auch eine Empty hull erstellen für unbekannte dataTypes
    //TODO Entscheiden, ob ich unterscheide zwischen primitiven DataTypes und Referenzen in meinen Objekten
    private String name;
    private String dataType;

    private Attribute() {
        // Empty constructor required as of Neo4j API 2.0.5
    }

    public Attribute(String name, String dataType) {
        this.name = name;
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
}
