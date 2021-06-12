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


@Node
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(example = ApiDocumentation.ATTRRIBUTE)
public class Attribute {

    @Id
    private String id;
    private String name;
    private String accessModifier;
    //isOnlySet if the class is of type java.util.list
    private Pojo genericType;

    @Relationship("DeclaringClass")
    private Pojo clazz;


}
