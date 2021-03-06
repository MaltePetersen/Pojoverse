package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.exception.PackageCreationFailedException;
import de.fh.kiel.advancedjava.pojomodel.exception.PackageDoesNotExistException;
import de.fh.kiel.advancedjava.pojomodel.exception.PackageNameNotAllowedException;
import de.fh.kiel.advancedjava.pojomodel.model.Package;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PackageRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PackageService {

    private final PojoRepository pojoRepository;
    private final PackageRepository packageRepository;

    public PackageService(PojoRepository pojoRepository, PackageRepository packageRepository) {
        this.pojoRepository = pojoRepository;
        this.packageRepository = packageRepository;
    }

    public List<Pojo> getPojos(String packageName) {
        if (validPackageNameStructure(packageName)) {
            var aPackage = packageRepository.findById(packageName).orElseThrow(() -> new PackageDoesNotExistException(packageName));
            return getPojosFromSubPackages(aPackage);
        }
        return Collections.emptyList();
    }

    private List<Pojo> getPojosFromSubPackages(Package aPackage) {
        var pojos = pojoRepository.findAllByaPackage_Id(aPackage.getId());
        if (aPackage.getSubPackage() == null)
            return pojos;
        pojos.addAll(getPojosFromSubPackages(aPackage.getSubPackage()));
        return pojos;
    }

    private boolean validPackageNameStructure(String packageName) {
        var regex = "^([A-Za-z]{1}[A-Za-z\\d_]*\\.)*[A-Za-z][A-Za-z\\d_]*$";
        return packageName.matches(regex);
    }

    public Package createPackage(String packageName) {
        return packageRepository.findById(packageName).orElseGet(() -> generatePackageRecursive(packageName));
    }

    private Package generatePackageRecursive(String packageName) {
        if (validPackageNameStructure(packageName)) {
            var aPackage = packageName.split("\\.");
            var current = new ArrayList<String>();
            var complete = new ArrayList<String>();
            Collections.addAll(current, aPackage);
            Collections.addAll(complete, aPackage);
            var generatePackage = generatePackage(current, complete);
            packageRepository.save(generatePackage);
            return packageRepository.findById(packageName).orElseThrow(() -> new PackageCreationFailedException(packageName));
        }
        throw new PackageNameNotAllowedException(packageName);
    }

    /**
     * @Jan-Phillip Thanks for the code example on how to optimize this recursive function.
     * I tried it but it did not work out. Therefore I decided to focus on other aspects
     * because I know from my experience it is easy to sink hours into making a recursive
     * function work properly and the benefit is a bit less code and minimal Optimisation.
     * Or other words there were just more fun parts to focus on :-)
     */
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
        aPackage.setSubPackage(generatePackage(current, complete));
        return aPackage;
    }

    private String combineArraysToGetId(ArrayList<String> current, ArrayList<String> complete) {
        var pos = complete.size() - current.size();
        var arr = complete.subList(0, pos + 1);
        return String.join(".", arr);
    }


}
