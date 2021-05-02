package de.fh.kiel.advancedjava.pojomodel.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Package {
    private String name;
    private Package parent;
}
