package de.fh.kiel.advancedjava.pojomodel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PojoStatistics {
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
