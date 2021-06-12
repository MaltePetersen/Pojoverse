package de.fh.kiel.advancedjava.pojomodel.controller;


import de.fh.kiel.advancedjava.pojomodel.dto.PojoEmptyHullDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoStatisticsDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.NoValidBase64Exception;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.JavaFileService;
import de.fh.kiel.advancedjava.pojomodel.service.PojoService;
import de.fh.kiel.advancedjava.pojomodel.service.PojoStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Base64 Class Upload", description = "On this endpoint the user can upload base64 encoded class files. " +
            "This endpoint is not the most useful for a normal user but really useful for integration testing, because we can handle the endpoint like" +
            "any other REST-controller.")
    public ResponseEntity<Pojo> createPojo(@Parameter(schema = @Schema(example = ApiDocumentation.BASE_64_POJO)) @RequestBody() String base64EncodedByteCodePojo) {
        byte[] pojoAsByteCode;

        try {
            pojoAsByteCode = Base64.getDecoder().decode(base64EncodedByteCodePojo);
        } catch (IllegalArgumentException i) {
            throw new NoValidBase64Exception();
        }
        var pojo = pojoService.readByteCodeAndCreatePojo(pojoAsByteCode);

        return ResponseEntity.ok(pojo);
    }

    @Operation(summary = "Upload a class as multipart files", description = "On this endpoint the user transform a multipart class files to a pojo")
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Pojo> createPojo(
            @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(pojoService.readByteCodeAndCreatePojo(file.getBytes()));
    }

    @PostMapping("emptyHull")
    @Operation(summary = "Create empty hulls", description = "On this endpoint the user can create empty hulls with json")
    public ResponseEntity<Pojo> createPojo(@RequestBody() PojoEmptyHullDTO pojoEmptyHullDTO) {
        var pojo = pojoService.createPojoEmptyHullFromJSON(pojoEmptyHullDTO);

        return ResponseEntity.ok(pojo);
    }

    @DeleteMapping("/{name}")
    @Operation(summary = "Delete a pojo", description = "On this endpoint the user can delete pojos")
    public ResponseEntity<Object> deletePojo(@Parameter(name = "Classname", description = "including the packagename", example = "package.class") @PathVariable("name") String pojoName) {
        pojoService.deletePojo(pojoName);
       return ResponseEntity.ok().build();
    }

    @GetMapping("/class/{name}")
    @Operation(summary = "Java File from Pojo", description = "On this endpoint the user can generate a Java file from a pojo")
    public ResponseEntity<String> generateJavaCode(@Parameter(name = "Classname", description = "including the packagename", example = "package.class") @PathVariable("name") String pojoName) {
        return ResponseEntity.ok(javaFileService.createJavaFile(pojoName));
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get a pojo", description = "On this endpoint the user can get a pojo ressource")
    public ResponseEntity<Pojo> getPojo(@Parameter(name = "Classname", description = "including the packagename", example = "package.class") @PathVariable("name") String pojoName) {
        return ResponseEntity.ok(pojoService.getPojo(pojoName));
    }

    @GetMapping("statistics/{name}")
    @Operation(summary = "Generate pojo statistics", description = "On this endpoint the user can generate pojo statsitics")
    public ResponseEntity<PojoStatisticsDTO> getPojoStatistics(@Parameter(name = "Classname", description = "including the packagename", example = "package.class") @PathVariable("name") String pojoName) {
        return ResponseEntity.ok(pojoStatisticsService.getStatistics(pojoName));
    }


}
