package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.exception.PojoAlreadyExistsException;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.model.PojoInfo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PackageRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PojosService {

    private final PojoRepository pojoRepository;
    private final AttributeRepository attributeRepository;
    private final ASMFacadeService asmFacadeService;
    private final PojoFacadeService pojoFacadeService;
    private final PackageRepository packageRepository;
    private final JarReaderService jarReaderService;

    PojosService(JarReaderService jarReaderService ,PackageRepository packageRepository, PojoRepository pojoRepository, AttributeRepository attributeRepository, ASMFacadeService asmFacadeService, PojoFacadeService pojoFacadeService) {
        this.jarReaderService = jarReaderService;
        this.pojoRepository = pojoRepository;
        this.attributeRepository = attributeRepository;
        this.asmFacadeService = asmFacadeService;
        this.pojoFacadeService = pojoFacadeService;
        this.packageRepository = packageRepository;
    }

    public List<Pojo> getAllPojos() {
        return pojoRepository.findAll();
    }

    public List<Pojo> importPojos(List<Pojo> pojos) {
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
        packageRepository.deleteAll();
        pojoRepository.saveAll(pojos);
        return pojoRepository.findAll();
    }


    public List<Pojo> savePojos(byte[] jar) throws IOException {
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
