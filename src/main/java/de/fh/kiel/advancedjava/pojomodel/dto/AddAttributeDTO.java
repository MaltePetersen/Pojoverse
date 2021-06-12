package de.fh.kiel.advancedjava.pojomodel.dto;

import de.fh.kiel.advancedjava.pojomodel.controller.ApiDocumentation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * genericType will only be used if the attribute is of type java.util.list
 */
@Data
@AllArgsConstructor
@Schema(example = ApiDocumentation.ADD_ATTRIBUTE_DTO)
public class AddAttributeDTO {
    private String name;
    private String type;
    private String visibility;
    private String genericType;
}
