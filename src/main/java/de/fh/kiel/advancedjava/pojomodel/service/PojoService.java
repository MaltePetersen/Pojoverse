package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.AttributeDeleteDTO;
import de.fh.kiel.advancedjava.pojomodel.dto.PojoEmptyHullDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.AttributeDoesNotExistException;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExistsException;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExistException;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.model.PojoInfo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PackageRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import de.fh.kiel.advancedjava.pojomodel.util.ParseUtil;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PojoService {


    private final PojoRepository pojoRepository;
    private final PojoFacadeService pojoFacadeService;
    private final ASMFacadeService asmFacadeService;
    private final PackageRepository packageRepository;
    private final JarReaderService jarReaderService;
    private final AttributeRepository attributeRepository;


    PojoService(AttributeRepository attributeRepository, JarReaderService jarReaderService ,PackageRepository packageRepository, PojoRepository pojoRepository, ASMFacadeService asmFacadeService, PojoFacadeService pojoFacadeService
    ) {
        this.attributeRepository = attributeRepository;
        this.packageRepository = packageRepository;
        this.jarReaderService = jarReaderService;
        this.pojoRepository = pojoRepository;
        this.asmFacadeService = asmFacadeService;
        this.pojoFacadeService = pojoFacadeService;
    }

    public Pojo readByteCodeAndCreatePojo(byte[] clazz) {

        var pojoInfo = this.asmFacadeService.read(clazz);

        return pojoFacadeService.createPojo(pojoInfo);
    }

    public Pojo createPojoEmptyHullFromJSON(PojoEmptyHullDTO emptyHull) {

        var completePath = ParseUtil.parseCompletePath(emptyHull.getPackageName(), emptyHull.getClassName());

        if (pojoRepository.existsById(completePath))
            throw new PojoAlreadyExistsException(completePath);

        return pojoFacadeService.createEmptyHull(completePath, emptyHull.getClassName(), emptyHull.getPackageName());
    }

    public void deletePojo(String pojoName) {
        var pojo = getPojo(pojoName);

        pojoFacadeService.delete(pojo);
    }



    public Pojo getPojo(String completePath) {
        return pojoRepository.findById(completePath).orElseThrow(() -> new PojoDoesNotExistException(completePath));
    }

    public List<Pojo> getAllPojos() {
        return pojoRepository.findAll();
    }

    public List<Pojo> importPojos(List<Pojo> pojos) {

        pojoFacadeService.deleteAllRessources();
        pojos.forEach(pojoFacadeService::createPojo);
        return pojoRepository.findAll();
    }




    public List<Pojo> savePojos(byte[] jar) {
        var pojoInfos = jarReaderService.read(jar);

        checkIfAPojoAlreadyExist(pojoInfos);
        pojoInfos.forEach(pojoFacadeService::createPojo);

        return pojoRepository.findAll();
    }

    private void checkIfAPojoAlreadyExist(List<PojoInfo> pojoInfos){
        pojoInfos.forEach(pojoInfo -> {
            var pojo = pojoRepository.findById(pojoInfo.getCompletePath());
            if (pojo.isPresent() && !pojo.get().isEmptyHull())
                throw new PojoAlreadyExistsException(pojoInfo.getCompletePath());
        });

    }
}


