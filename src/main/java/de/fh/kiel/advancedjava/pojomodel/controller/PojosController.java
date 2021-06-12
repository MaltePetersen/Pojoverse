package de.fh.kiel.advancedjava.pojomodel.controller;

import de.fh.kiel.advancedjava.pojomodel.dto.ExportDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.NoValidBase64Exception;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.PojoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Base64 Jar Upload", description = "On this endpoint the user can upload base64 encoded Jar. " +
            "This endpoint is not the most useful for a normal user but really useful for integration testing, because we can handle the endpoint like" +
            "any other REST-controller.")
    public ResponseEntity<List<Pojo>> createPojos(@Parameter(schema = @Schema(example = ApiDocumentation.BASE_64_JAR)) @RequestBody() String base64EncodedJar) {
        byte[] pojoAsByteCode;

        try {
            pojoAsByteCode = Base64.getDecoder().decode(base64EncodedJar);
        } catch (IllegalArgumentException i) {
            throw new NoValidBase64Exception();
        }

        return ResponseEntity.ok(pojoService.savePojos(pojoAsByteCode));
    }

    @PostMapping("/multiple")
    @Operation(summary = "Import an ExportDTO", description = "On this endpoint the user can import with an ExportDTO. Caution: " +
            "The import will delete all other data")
    public ResponseEntity<ExportDTO> createPojos(@RequestBody() ExportDTO exportDTOs) {

        return ResponseEntity.ok(pojoService.importPojos(exportDTOs));
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Multipart Jar Upload", description = "On this endpoint the user can upload a jar." +
            "In the path: src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/jars/jar-file.jar +" +
            "is already a jar-file for testing with multiple classes in it")
    public ResponseEntity<List<Pojo>> createPojos(
            @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(pojoService.savePojos(file.getBytes()));
    }

    @Operation(summary = "Get all pojos and packages", description = "This endpoint returns an exportDTO including all pojos and packages. " +
            "The packages are also included because without them an import of the files would not yield the same graph as before")
    @GetMapping
    public ExportDTO getPojos() {
        return pojoService.getAllPojos();
    }


}
