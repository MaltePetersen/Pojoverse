package de.fh.kiel.advancedjava.pojomodel.controller;

import de.fh.kiel.advancedjava.pojomodel.dto.AddAttributeDTO;
import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import de.fh.kiel.advancedjava.pojomodel.model.Primitive;
import de.fh.kiel.advancedjava.pojomodel.model.Reference;
import de.fh.kiel.advancedjava.pojomodel.service.AttributeService;
import de.fh.kiel.advancedjava.pojomodel.service.PojoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("attribute")
public class AttributeController {
    private final AttributeService attributeService;

    AttributeController(AttributeService attributeService){
        this.attributeService = attributeService;
    }

    @PostMapping("primitive/{className}")
    public ResponseEntity<Primitive> addPrimitive(@PathVariable String className, @RequestBody AddAttributeDTO addAttributeDTO) throws Exception {
        return ResponseEntity.ok(this.attributeService.addPrimitive(className, addAttributeDTO));
    }
    @PostMapping("reference/{className}")
    public ResponseEntity<Reference> addReference(@PathVariable String className, @RequestBody AddAttributeDTO addAttributeDTO) throws Exception {
        return ResponseEntity.ok(this.attributeService.addReference(className, addAttributeDTO));
    }
}

