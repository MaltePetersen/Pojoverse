package de.fh.kiel.advancedjava.pojomodel.dto;
import de.fh.kiel.advancedjava.pojomodel.model.Package;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportDTO {
    List<Pojo> pojoList;
    List<Package> packageList;
}
