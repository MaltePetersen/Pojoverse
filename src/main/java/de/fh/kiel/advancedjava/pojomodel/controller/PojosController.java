package de.fh.kiel.advancedjava.pojomodel.controller;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.PojosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("pojos")
public class PojosController {
    private final PojosService pojosService;

    PojosController(PojosService pojosService){
        this.pojosService = pojosService;
    }
    @PostMapping
    public ResponseEntity<?> createPojos(@RequestBody() String base64EncodedJar) throws MalformedURLException, ClassNotFoundException {
        byte[] pojoAsByteCode;

        try {
            pojoAsByteCode = Base64.getDecoder().decode(base64EncodedJar);
        } catch (IllegalArgumentException i) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        pojosService.extractPojos(pojoAsByteCode);

        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    @GetMapping
    public List<Pojo> getPojos(){
        return pojosService.getAllPojos();
    }
}
