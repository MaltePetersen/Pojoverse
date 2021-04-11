package de.fh.kiel.advancedjava.pojomodel.controller;


import de.fh.kiel.advancedjava.pojomodel.dto.AttributeChangeDTO;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.PojoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;


@RestController()
@RequestMapping("pojo")
public class PojoController {
    private final PojoService pojoService;
    PojoController(PojoService pojoService) {
        this.pojoService = pojoService;
    }


    @PostMapping
    public ResponseEntity<Pojo> createPojo(@RequestBody() String base64EncodedByteCodePojo) throws Exception {
        byte[] pojoAsByteCode;

        try {
            pojoAsByteCode = Base64.getDecoder().decode(base64EncodedByteCodePojo);
        } catch (IllegalArgumentException i) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        Pojo pojo = pojoService.createPojo(pojoAsByteCode);
        if(pojo == null)
            throw new Exception("Pojo already exists and is not and empty hull");

        return ResponseEntity.ok(pojo);

    }
    @PutMapping
    public ResponseEntity<?> deleteAttribute(@RequestBody() AttributeChangeDTO attributeChangeDTO){
        if(pojoService.changeAttribute(attributeChangeDTO)){
            return ResponseEntity.ok("Successfull");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Provided Parameter is invalid");
    }
    //TODO Query if has relation
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deletePojo(@PathVariable("name") String pojoName){
        if(pojoService.deletePojo(pojoName)){
            return ResponseEntity.ok("Successfull");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


}
