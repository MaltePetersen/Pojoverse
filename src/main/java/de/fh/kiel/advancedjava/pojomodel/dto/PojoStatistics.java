package de.fh.kiel.advancedjava.pojomodel.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
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
