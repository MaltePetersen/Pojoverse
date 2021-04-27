package de.fh.kiel.advancedjava.pojomodel.model;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Set;
@Data
@AllArgsConstructor
public class PojoInfo {

    private String completePath, className, packageName, parentClassCompletePath, parentClassName, parentClassPackageName;

    private Set<AttributeInfo> attributes;

    private Set<String> interfaces;


}
