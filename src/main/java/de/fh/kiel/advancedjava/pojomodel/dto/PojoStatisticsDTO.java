package de.fh.kiel.advancedjava.pojomodel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(example = "{\n" +
        "  \"classname\": \"DefaultClass\",\n" +
        "  \"packageName\": \"exampleData\",\n" +
        "  \"numberOfAttributes\": 2,\n" +
        "  \"parentClassName\": \"Object\",\n" +
        "  \"implementedInterfaces\": [],\n" +
        "  \"numberOfDirectSubClasses\": 0,\n" +
        "  \"numberOfAttributesThatHaveTheCorrespondingDataType\": 0,\n" +
        "  \"numberOfClassesInTheSamePackage\": 1,\n" +
        "  \"numberOfClassesWithTheSameName\": 1\n" +
        "}")
public class PojoStatisticsDTO {
    private String classname;
    private String packageName;
    private int numberOfAttributes;
    private String parentClassName;
    private Set<String> implementedInterfaces;
    private int numberOfDirectSubClasses;
    private int numberOfAttributesThatHaveTheCorrespondingDataType;
    private int numberOfClassesInTheSamePackage;
    private int numberOfClassesWithTheSameName;
}
