package de.fh.kiel.advancedjava.pojomodel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Node
@Schema(example = "{\n" +
        "              \"id\": \"java.lang\",\n" +
        "              \"name\": \"lang\",\n" +
        "              \"subPackage\": null\n" +
        "            }")
public class Package {
    @Id
    private String id;
    private String name;


    @Relationship(type = "sub")
    private Package subPackage;
}
