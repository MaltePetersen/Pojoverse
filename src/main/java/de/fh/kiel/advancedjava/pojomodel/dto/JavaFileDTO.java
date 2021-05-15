package de.fh.kiel.advancedjava.pojomodel.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class JavaFileDTO {

    private String className;
    private String packageName;
    private String superClassName;
    private Set<String> attributes;
    private Set<String> imports;
    private String interfaces;

    public JavaFileDTO(String className, String packageName) {
        this.className = className;
        this.packageName = packageName;
        this.attributes = new HashSet<>();
        this.imports = new HashSet<>();
    }
}
