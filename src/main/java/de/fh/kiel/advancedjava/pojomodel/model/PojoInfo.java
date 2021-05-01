package de.fh.kiel.advancedjava.pojomodel.model;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Set;
@Data
@AllArgsConstructor
public class PojoInfo {

    private String completePath;
    private String className;
    private String packageName;
    private String parentClassCompletePath;
    private String parentClassName;
    private String parentClassPackageName;

    private Set<AttributeInfo> attributes;

    private Set<String> interfaces;


}
