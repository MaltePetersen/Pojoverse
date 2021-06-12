package de.fh.kiel.advancedjava.pojomodel.model;

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
@Schema(example = "      {\n" +
        "        \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClassname\",\n" +
        "        \"name\": \"name\",\n" +
        "        \"accessModifier\": \"private\",\n" +
        "        \"genericType\": null,\n" +
        "        \"clazz\": {\n" +
        "          \"emptyHull\": true,\n" +
        "          \"completePath\": \"java.lang.String\",\n" +
        "          \"className\": \"String\",\n" +
        "          \"attributes\": [],\n" +
        "          \"parentClass\": null,\n" +
        "          \"interfaces\": [],\n" +
        "          \"apackage\": {\n" +
        "            \"id\": \"java.lang\",\n" +
        "            \"name\": \"lang\",\n" +
        "            \"subPackage\": null\n" +
        "          }\n" +
        "        }\n" +
        "      }")
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
