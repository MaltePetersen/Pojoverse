package de.fh.kiel.advancedjava.pojomodel.dto;

import de.fh.kiel.advancedjava.pojomodel.controller.ApiDocumentation;
import de.fh.kiel.advancedjava.pojomodel.model.Package;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(example = ApiDocumentation.EXPORT_DTO)
public class ExportDTO {
    List<Pojo> pojoList;
    List<Package> packageList;
}
