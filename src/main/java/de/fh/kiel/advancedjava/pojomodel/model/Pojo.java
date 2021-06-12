package de.fh.kiel.advancedjava.pojomodel.model;

import de.fh.kiel.advancedjava.pojomodel.controller.ApiDocumentation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(example = ApiDocumentation.POJO)
public class Pojo {

    private boolean emptyHull;

    @Id
    private String completePath;
    private String className;
    private Package aPackage;


    @Relationship(type = "attributes")
    private Set<Attribute> attributes = new HashSet<>();

    @Relationship(type = "parent")
    private Pojo parentClass;

    @Relationship(type = "interfaces")
    private Set<String> interfaces = new HashSet<>();


    public Pojo(String completePath, String className, Package aPackage) {
        this.emptyHull = true;
        this.completePath = completePath;
        this.className = className;
        this.aPackage = aPackage;
    }


}
