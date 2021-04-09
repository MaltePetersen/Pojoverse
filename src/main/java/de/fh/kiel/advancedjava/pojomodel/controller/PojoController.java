package de.fh.kiel.advancedjava.pojomodel.controller;


import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.PojoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;


@RestController
public class PojoController {
    private final PojoService pojoService;

    PojoController(PojoService pojoService){
        this.pojoService = pojoService;
    }


    @PostMapping("/pojo")
    public Pojo createPojo(@RequestBody() String base64EncodedByteCodePojo)  {

        byte[] pojoAsByteCode =   Base64.getDecoder().decode(base64EncodedByteCodePojo);
        return pojoService.createPojo(pojoAsByteCode);
}


}
