package de.fh.kiel.advancedjava.pojomodel.controller;

import de.fh.kiel.advancedjava.pojomodel.exception.NoValidBase64Exception;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.PojoService;
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
    private final PojoService pojoService;

    PojosController(PojoService pojoService) {
        this.pojoService = pojoService;
    }

    @PostMapping
    public ResponseEntity<?> createPojos(@RequestBody() String base64EncodedJar) throws IOException {
        byte[] pojoAsByteCode;

        try {
            pojoAsByteCode = Base64.getDecoder().decode(base64EncodedJar);
        } catch (IllegalArgumentException i) {
            throw new NoValidBase64Exception();
        }

        return ResponseEntity.ok(pojoService.savePojos(pojoAsByteCode));
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<Pojo>> createPojos(@RequestBody() List<Pojo> pojos) {

        return ResponseEntity.ok(pojoService.importPojos(pojos));
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Pojo>> createPojos(
            @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(pojoService.savePojos(file.getBytes()));
    }

    @GetMapping
    public List<Pojo> getPojos() {
        return pojoService.getAllPojos();
    }


}
