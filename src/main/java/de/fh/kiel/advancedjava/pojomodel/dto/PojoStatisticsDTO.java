package de.fh.kiel.advancedjava.pojomodel.dto;

import de.fh.kiel.advancedjava.pojomodel.controller.ApiDocumentation;
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
@Schema(example = ApiDocumentation.POJO_STATS)
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
