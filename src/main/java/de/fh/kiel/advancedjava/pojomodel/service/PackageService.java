package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
public class PackageService {

    private final PojoRepository pojoRepository;

    public PackageService(PojoRepository pojoRepository) {
        this.pojoRepository = pojoRepository;
    }

    public List<Pojo> getPojos(String packageName){
        if(validPackageNameStructure(packageName)){
            return pojoRepository.findAllPojosContainedInThisPackageWithAllSubPackages(packageName);
        }
        return Collections.emptyList();
    }
    private boolean validPackageNameStructure(String packageName){
        var regex = "^([A-Za-z]{1}[A-Za-z\\d_]*\\.)*[A-Za-z][A-Za-z\\d_]*$";
        return packageName.matches(regex);
    }




}
