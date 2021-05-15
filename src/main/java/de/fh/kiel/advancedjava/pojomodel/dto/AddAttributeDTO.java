package de.fh.kiel.advancedjava.pojomodel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
genericType will only be used if the attribute is of type java.util.list
 */
@Data
@AllArgsConstructor
public class AddAttributeDTO {
    private String name;
    private String type;
    private String visibility;
    private String genericType;
}
