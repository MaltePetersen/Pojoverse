package de.fh.kiel.advancedjava.pojomodel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttributeDeleteDTO {
    private String className;
    private String packageName;
    private String attributeName;

}
