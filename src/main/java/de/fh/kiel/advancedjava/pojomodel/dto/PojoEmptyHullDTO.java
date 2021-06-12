package de.fh.kiel.advancedjava.pojomodel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(example = "{\n" +
        "  \"packageName\": \"de.fh.test\",\n" +
        "  \"className\": \"hello\"\n" +
        "}")
public class PojoEmptyHullDTO {

    private String packageName;
    private String className;

}
