package de.fh.kiel.advancedjava.pojomodel.controller;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.service.PackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("package")
public class PackageController {

    private final PackageService packageService;
    PackageController(PackageService packageService){
        this.packageService = packageService;
    }

    @GetMapping("/{packageName}")
    public ResponseEntity<List<Pojo>> addPrimitive(@PathVariable String packageName) {
        return ResponseEntity.ok(this.packageService.getPojos(packageName));
    }

}

