package de.fh.kiel.advancedjava.pojomodel.dto;

import de.fh.kiel.advancedjava.pojomodel.controller.ApiDocumentation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(example = ApiDocumentation.ATTRIBUTE_DELETE_DTO)
public class AttributeDeleteDTO {
    private String className;
    private String packageName;
    private String attributeName;

}
