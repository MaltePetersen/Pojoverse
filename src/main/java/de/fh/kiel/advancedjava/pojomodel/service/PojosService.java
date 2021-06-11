package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.exception.CouldNotReadJarException;
import de.fh.kiel.advancedjava.pojomodel.facade.PojoFacadeService;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.model.PojoInfo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

@Service
public class PojosService {
    private final PojoRepository pojoRepository;

    private final AttributeRepository attributeRepository;
    private final ASMFacadeService asmFacadeService;
    private final PojoFacadeService pojoFacadeService;


    PojosService(PojoRepository pojoRepository, AttributeRepository attributeRepository, ASMFacadeService asmFacadeService, PojoFacadeService pojoFacadeService) {
        this.pojoRepository = pojoRepository;
        this.attributeRepository = attributeRepository;
        this.asmFacadeService = asmFacadeService;
        this.pojoFacadeService = pojoFacadeService;
    }

    public List<Pojo> getAllPojos() {
        return pojoRepository.findAll();
    }

    public List<Pojo> importPojos(List<Pojo> pojos) {
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();
        pojoRepository.saveAll(pojos);
        return pojoRepository.findAll();
    }

    public List<Pojo> extractJar(byte[] input) throws IOException {
        var file = new File("temp.jar");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(input);
        } catch (IOException e) {
            throw new CouldNotReadJarException();
        }
        return savePojos(file);
    }

    private List<Pojo> savePojos(File file) throws IOException {
        var pojoInfos = loadClasses(file);
        pojoInfos.forEach(pojoFacadeService::createPojo);

        return pojoRepository.findAll();
    }

    private List<PojoInfo> loadClasses(File jarFile) throws IOException {
        var classes = new ArrayList<PojoInfo>();
        var jar = new JarFile(jarFile);
        Stream<JarEntry> str = jar.stream();
        str.forEach(z -> readJar(jar, z, classes));
        jar.close();
        return classes;
    }

    private void readJar(JarFile jar, JarEntry entry, List<PojoInfo> classes) {
        var name = entry.getName();
        try (var inputStream = jar.getInputStream(entry)) {
            if (name.endsWith(".class")) {
                byte[] bytes = IOUtils.toByteArray(inputStream);
                var pojoInfo = asmFacadeService.read(bytes);
                classes.add(pojoInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }








}
