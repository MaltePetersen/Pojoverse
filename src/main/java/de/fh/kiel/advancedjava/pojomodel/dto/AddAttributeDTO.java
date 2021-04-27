package de.fh.kiel.advancedjava.pojomodel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddAttributeDTO {
    private String name;
    private String type;
    private String visibility;
    //Will only be used if the attribute is of type java.util.list;
    private String genericType;
}
