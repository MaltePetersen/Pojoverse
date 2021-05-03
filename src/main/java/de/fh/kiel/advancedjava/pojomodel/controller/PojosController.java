package de.fh.kiel.advancedjava.pojomodel.controller;

import de.fh.kiel.advancedjava.pojomodel.exception.NoValidBase64;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.PojosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController()
@RequestMapping("pojos")
public class PojosController {
    private final PojosService pojosService;

    PojosController(PojosService pojosService){
        this.pojosService = pojosService;
    }
    @PostMapping
    public ResponseEntity<?> createPojos(@RequestBody() String base64EncodedJar)  {
        byte[] pojoAsByteCode;

        try {
            pojoAsByteCode = Base64.getDecoder().decode(base64EncodedJar);
        } catch (IllegalArgumentException i) {
            throw new NoValidBase64();
        }
        pojosService.extractPojos(pojoAsByteCode);

        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    @GetMapping
    public List<Pojo> getPojos(){
        return pojosService.getAllPojos();
    }
    @PostMapping("/multiple")
    public ResponseEntity<List<Pojo>> importPojos(@RequestBody() List<Pojo> pojos ) {

    return ResponseEntity.ok(pojosService.importPojos(pojos));
    }
    @PostMapping(  path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Pojo>> handleUpload(
            @RequestPart("file") MultipartFile file) throws IOException {
        return  ResponseEntity.ok(pojosService.addToDB(file));
    }
}
