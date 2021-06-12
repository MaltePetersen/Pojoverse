package de.fh.kiel.advancedjava.pojomodel.controller;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.PackageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController()
@RequestMapping("package")
public class PackageController {

    private final PackageService packageService;

    PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/{packageName}")
    @Operation(summary = "Get al pojo in a package", description = "On this endpoint the user can get all pojos in package")
    public ResponseEntity<List<Pojo>> getAllPojosFromPackage(@PathVariable String packageName) {
        return ResponseEntity.ok(this.packageService.getPojos(packageName));
    }

}

