package de.fh.kiel.advancedjava.pojomodel.controller;


import de.fh.kiel.advancedjava.pojomodel.dto.AttributeDeleteDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoEmptyHullDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoStatisticsDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.NoValidBase64Exception;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.JavaFileService;
import de.fh.kiel.advancedjava.pojomodel.service.PojoService;
import de.fh.kiel.advancedjava.pojomodel.service.PojoStatisticsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


@RestController()
@RequestMapping("pojo")
public class PojoController {
    private final PojoService pojoService;
    private final JavaFileService javaFileService;
    private final PojoStatisticsService pojoStatisticsService;

    PojoController(PojoService pojoService, JavaFileService javaFileService, PojoStatisticsService pojoStatisticsService) {
        this.pojoService = pojoService;
        this.javaFileService = javaFileService;
        this.pojoStatisticsService = pojoStatisticsService;
    }


    @PostMapping
    public ResponseEntity<Pojo> createPojo(@RequestBody() String base64EncodedByteCodePojo) {
        byte[] pojoAsByteCode;

        try {
            pojoAsByteCode = Base64.getDecoder().decode(base64EncodedByteCodePojo);
        } catch (IllegalArgumentException i) {
            throw new NoValidBase64Exception();
        }
        var pojo = pojoService.readByteCodeAndCreatePojo(pojoAsByteCode);

        return ResponseEntity.ok(pojo);
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Pojo> createPojo(
            @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(pojoService.readByteCodeAndCreatePojo(file.getBytes()));
    }

    @PostMapping("emptyHull")
    public ResponseEntity<Pojo> createPojo(@RequestBody() PojoEmptyHullDTO pojoEmptyHullDTO) {
        var pojo = pojoService.createPojoEmptyHullFromJSON(pojoEmptyHullDTO);

        return ResponseEntity.ok(pojo);
    }

    @PutMapping
    public ResponseEntity<Pojo> deleteAttribute(@RequestBody() AttributeDeleteDTO attributeDeleteDTO) {
        return ResponseEntity.ok(pojoService.deleteAttribute(attributeDeleteDTO));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deletePojo(@PathVariable("name") String pojoName) {
        pojoService.deletePojo(pojoName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/class/{name}")
    public ResponseEntity<String> generateJavaCode(@PathVariable("name") String pojoName) {
        return ResponseEntity.ok(javaFileService.createJavaFile(pojoName));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Pojo> getPojo(@PathVariable("name") String pojoName) {
        return ResponseEntity.ok(pojoService.getPojo(pojoName));
    }

    @GetMapping("statistics/{name}")
    public ResponseEntity<PojoStatisticsDTO> getPojoStatistics(@PathVariable("name") String pojoName) {
        return ResponseEntity.ok(pojoStatisticsService.getStatistics(pojoName));
    }


}
