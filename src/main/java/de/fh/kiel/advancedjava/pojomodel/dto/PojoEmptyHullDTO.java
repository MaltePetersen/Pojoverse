package de.fh.kiel.advancedjava.pojomodel.dto;

import de.fh.kiel.advancedjava.pojomodel.controller.ApiDocumentation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(example = ApiDocumentation.POJO_EMPTY_HULL_DTO)
public class PojoEmptyHullDTO {

    private String packageName;
    private String className;

}
