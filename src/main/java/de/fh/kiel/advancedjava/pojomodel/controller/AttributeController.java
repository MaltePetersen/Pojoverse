package de.fh.kiel.advancedjava.pojomodel.controller;

import de.fh.kiel.advancedjava.pojomodel.dto.AddAttributeDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.AttributeDeleteDTO;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("attribute")
public class AttributeController {
    private final AttributeService attributeService;

    AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @Operation(summary = "Add an attribute to a pojo")
    @ApiResponse(responseCode = "200", description = "Attribute was succesfully added to the pojo")
    @PostMapping("/{className}")
    public ResponseEntity<Pojo> addAttribute(@Parameter(name = "Classname", description = "including the packagename", example = "package.class") @PathVariable String className, @RequestBody AddAttributeDTO addAttributeDTO) {
        return ResponseEntity.ok(this.attributeService.addAttribute(className, addAttributeDTO));
    }

    @Operation(summary = "Delete an attribute of a pojo", description = "As a classname you have to add the full class name including the package Information, like this: package.class")
    @ApiResponse(responseCode = "200", description = "Attribute was succesfully deleted from the pojo")
    @PutMapping
    public ResponseEntity<Pojo> deleteAttribute(@RequestBody() AttributeDeleteDTO attributeDeleteDTO) {
        return ResponseEntity.ok(attributeService.deleteAttribute(attributeDeleteDTO));
    }

}

