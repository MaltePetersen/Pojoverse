package de.fh.kiel.advancedjava.pojomodel.controller;


import de.fh.kiel.advancedjava.pojomodel.dto.AttributeChangeDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.NoValidBase64;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExists;
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
    public ResponseEntity<Pojo> createPojo(@RequestBody() String base64EncodedByteCodePojo) throws Exception {
        byte[] pojoAsByteCode;

        try {
            pojoAsByteCode = Base64.getDecoder().decode(base64EncodedByteCodePojo);
        } catch (IllegalArgumentException i) {
            throw new NoValidBase64();
        }
        Pojo pojo = pojoService.createPojo(pojoAsByteCode);

      //  Pojo pojo = pojoService.createPojo(pojoAsByteCode);
        //if(pojo == null)
          //  throw new PojoAlreadyExists("Pojo");

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

    @GetMapping("/{name}")
    public ResponseEntity<String> javaCode(@PathVariable("name") String pojoName){
       return ResponseEntity.ok(tymeLeafTemplateService.createJavaFile(pojoName));
    }
}
