package de.fh.kiel.advancedjava.pojomodel.controller;


import de.fh.kiel.advancedjava.pojomodel.dto.AttributeChangeDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoEmptyHullDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoStatistics;
import de.fh.kiel.advancedjava.pojomodel.exception.NoValidBase64;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.PojoService;
import de.fh.kiel.advancedjava.pojomodel.service.PojoStatisticsService;
import de.fh.kiel.advancedjava.pojomodel.service.TymeLeafTemplateService;
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
    private final TymeLeafTemplateService tymeLeafTemplateService;
    private final PojoStatisticsService pojoStatisticsService;

    PojoController(PojoService pojoService, TymeLeafTemplateService tymeLeafTemplateService, PojoStatisticsService pojoStatisticsService) {
        this.pojoService = pojoService;
        this.tymeLeafTemplateService = tymeLeafTemplateService;
        this.pojoStatisticsService =pojoStatisticsService;
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
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deletePojo(@PathVariable("name") String pojoName){
        pojoService.deletePojo(pojoName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/class/{name}")
    public ResponseEntity<String> javaCode(@PathVariable("name") String pojoName){
       return ResponseEntity.ok(tymeLeafTemplateService.createOptimziedJavaFile(pojoName));
    }
    @GetMapping("/{name}")
    public ResponseEntity<Pojo> getPojo(@PathVariable("name") String pojoName){
        return ResponseEntity.ok(pojoService.getPojo(pojoName));
    }
    @GetMapping("statistics/{name}")
    public ResponseEntity<PojoStatistics> getPojoStatistics(@PathVariable("name") String pojoName){
        return ResponseEntity.ok(pojoStatisticsService.getStatistics(pojoName));
    }
    @PostMapping(  path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Pojo> handleUpload(
            @RequestPart("file") MultipartFile file) throws IOException {
        return  ResponseEntity.ok(pojoService.readByteCodeAndCreatePojo(file.getBytes()));
    }
}
