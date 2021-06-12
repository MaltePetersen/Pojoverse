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

    private static final String base64JarExample = "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";
    private final PojoService pojoService;

    PojosController(PojoService pojoService) {
        this.pojoService = pojoService;
    }

    @PostMapping()
    @Operation(summary = "Base64 Jar Upload", description = "On this endpoint the user can upload base64 encoded Jar. " +
            "This endpoint is not the most useful for a normal user but really useful for integration testing, because we can handle the endpoint like" +
            "any other REST-controller.")
    public ResponseEntity<?> createPojos(@Parameter( schema = @Schema(example = base64JarExample)) @RequestBody() String base64EncodedJar) {
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
