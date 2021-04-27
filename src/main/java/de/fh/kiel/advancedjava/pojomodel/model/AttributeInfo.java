package de.fh.kiel.advancedjava.pojomodel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttributeInfo {
    String name, dataTypeName, accessModifier, className, packageName;
}
