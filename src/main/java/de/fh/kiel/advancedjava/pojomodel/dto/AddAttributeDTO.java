package de.fh.kiel.advancedjava.pojomodel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/*
genericType will only be used if the attribute is of type java.util.list
 */
@Data
@AllArgsConstructor
@Schema(example = "{\n" +
        "  \"name\": \"something\",\n" +
        "  \"type\": \"java.util.List\",\n" +
        "  \"visibility\": \"private\",\n" +
        "  \"genericType\": \"int\"\n" +
        "}\n")
public class AddAttributeDTO {
    private String name;
    private String type;
    private String visibility;
    private String genericType;
}
