package de.fh.kiel.advancedjava.pojomodel.model;

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
@Schema(example = "{\n" +
        "  \"emptyHull\": false,\n" +
        "  \"completePath\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitives\",\n" +
        "  \"className\": \"AnotherClassWithPrimitives\",\n" +
        "  \"packageName\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "  \"attributes\": [\n" +
        "    {\n" +
        "      \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesid\",\n" +
        "      \"name\": \"id\",\n" +
        "      \"accessModifier\": \"\",\n" +
        "      \"genericType\": null,\n" +
        "      \"clazz\": {\n" +
        "        \"emptyHull\": true,\n" +
        "        \"completePath\": \"java.lang.Integer\",\n" +
        "        \"className\": \"Integer\",\n" +
        "        \"packageName\": \"java.lang\",\n" +
        "        \"attributes\": [],\n" +
        "        \"parentClass\": null,\n" +
        "        \"interfaces\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesis\",\n" +
        "      \"name\": \"is\",\n" +
        "      \"accessModifier\": \"\",\n" +
        "      \"genericType\": null,\n" +
        "      \"clazz\": {\n" +
        "        \"emptyHull\": true,\n" +
        "        \"completePath\": \"java.lang.Boolean\",\n" +
        "        \"className\": \"Boolean\",\n" +
        "        \"packageName\": \"java.lang\",\n" +
        "        \"attributes\": [],\n" +
        "        \"parentClass\": null,\n" +
        "        \"interfaces\": null\n" +
        "      }\n" +
        "    }\n" +
        "  ],\n" +
        "  \"parentClass\": {\n" +
        "    \"emptyHull\": true,\n" +
        "    \"completePath\": \"java.lang.Object\",\n" +
        "    \"className\": \"Object\",\n" +
        "    \"packageName\": \"java.lang\",\n" +
        "    \"attributes\": [],\n" +
        "    \"parentClass\": null,\n" +
        "    \"interfaces\": null\n" +
        "  },\n" +
        "  \"interfaces\": []\n" +
        "}")
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
