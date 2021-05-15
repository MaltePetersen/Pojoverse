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
    private String superClassCompletePath;
    private String superClassName;
    private String superClassPackageName;

    private Set<AttributeInfo> attributeInfos;

    private Set<String> interfaces;


}
