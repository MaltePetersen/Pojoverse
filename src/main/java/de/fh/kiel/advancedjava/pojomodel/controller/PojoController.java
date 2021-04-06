package de.fh.kiel.advancedjava.pojomodel.controller;


import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.PojoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PojoController {
    PojoService pojoService;

    PojoController(PojoService pojoService){
        this.pojoService = pojoService;
    }


@PostMapping("/pojo")
    public Pojo createPojo(@RequestBody() String byteCodePojo)  {
    return pojoService.createPojo(byteCodePojo);
}


}
