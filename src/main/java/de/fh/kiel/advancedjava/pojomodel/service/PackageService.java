package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.exception.PackageNameNotAllowed;
import de.fh.kiel.advancedjava.pojomodel.model.Package;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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

    public Package createPackage(String packageName){
        if (validPackageNameStructure(packageName)) {
            var aPackage = packageName.split("\\.");
            var current = new ArrayList<String>();
            var complete = new ArrayList<String>();
            Collections.addAll(current, aPackage);
            Collections.addAll(complete, aPackage);
            return generatePackage(current, complete);
        }
        throw new PackageNameNotAllowed(packageName);
    }


    private Package generatePackage(ArrayList<String> current, ArrayList<String> complete) {
        Package aPackage;
        String id;
        if (current.equals(complete))
            aPackage = Package.builder().id(current.get(0)).name(current.get(0)).build();
        else {
            id = combineArraysToGetId(current, complete);
            aPackage = Package.builder().id(id).name(current.get(0)).build();
        }
        current.remove(0);

        if (current.isEmpty()) {
            return aPackage;
        }
        aPackage.setParent(generatePackage(current, complete));
        return aPackage;
    }

    private String combineArraysToGetId(ArrayList<String> current, ArrayList<String> complete){
        var pos = complete.size() - current.size();
        var arr = complete.subList(0,pos +1);
        return String.join(".", arr);
    }


}
