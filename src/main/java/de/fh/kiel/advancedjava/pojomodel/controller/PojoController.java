package de.fh.kiel.advancedjava.pojomodel.controller;


import de.fh.kiel.advancedjava.pojomodel.dto.AttributeChangeDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoEmptyHullDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.NoValidBase64;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.PojoService;
import de.fh.kiel.advancedjava.pojomodel.service.TymeLeafTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;


@RestController()
@RequestMapping("pojo")
public class PojoController {
    private final PojoService pojoService;
    private final TymeLeafTemplateService tymeLeafTemplateService;

    PojoController(PojoService pojoService, TymeLeafTemplateService tymeLeafTemplateService) {
        this.pojoService = pojoService;
        this.tymeLeafTemplateService = tymeLeafTemplateService;
    }


    @PostMapping
    public ResponseEntity<Pojo> createPojoFromByteCode(@RequestBody() String base64EncodedByteCodePojo) {
        byte[] pojoAsByteCode;

        try {
            pojoAsByteCode = Base64.getDecoder().decode(base64EncodedByteCodePojo);
        } catch (IllegalArgumentException i) {
            throw new NoValidBase64();
        }
        var pojo = pojoService.readByteCodeAndCreatePojo(pojoAsByteCode);

        return ResponseEntity.ok(pojo);
    }
    @PostMapping("emptyHull")
    public ResponseEntity<Pojo> createPojoFromJSON(@RequestBody() PojoEmptyHullDTO pojoEmptyHullDTO) {
        var pojo = pojoService.createPojoEmptyHullFromJSON(pojoEmptyHullDTO);

        return ResponseEntity.ok(pojo);
    }
    @PutMapping
    public ResponseEntity<Pojo> deleteAttribute(@RequestBody() AttributeChangeDTO attributeChangeDTO){
            return ResponseEntity.ok(pojoService.changeAttribute(attributeChangeDTO));
    }
    //TODO Query if has relation
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deletePojo(@PathVariable("name") String pojoName){
        pojoService.deletePojo(pojoName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/class/{name}")
    public ResponseEntity<String> javaCode(@PathVariable("name") String pojoName){
       return ResponseEntity.ok(tymeLeafTemplateService.createJavaFile(pojoName));
    }
    @GetMapping("/{name}")
    public ResponseEntity<Pojo> getPojo(@PathVariable("name") String pojoName){
        return ResponseEntity.ok(pojoService.getPojo(pojoName));
    }
}
