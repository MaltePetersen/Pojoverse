package de.fh.kiel.advancedjava.pojomodel.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

public class Interface {
    @Id
    @GeneratedValue
    private Long id;


}
