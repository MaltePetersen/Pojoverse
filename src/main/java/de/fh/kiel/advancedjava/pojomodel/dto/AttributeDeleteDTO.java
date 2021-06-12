package de.fh.kiel.advancedjava.pojomodel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(example = "{\n" +
        "  \"className\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass\",\n" +
        "  \"packageName\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "  \"attributeName\": \"name\"\n" +
        "}\n")
public class AttributeDeleteDTO {
    private String className;
    private String packageName;
    private String attributeName;

}
