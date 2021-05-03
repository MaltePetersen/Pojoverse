package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.model.PojoInfo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.apache.commons.io.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PojosService {
    private final PojoRepository pojoRepository;

    private final AttributeRepository attributeRepository;
    @Autowired
    private ASMWrapperService asmWrapperService;
    private final PojoService pojoService;


    PojosService(PojoService pojoService, PojoRepository pojoRepository, AttributeRepository attributeRepository, ASMWrapperService asmWrapperService ) throws IOException {
        pojoRepository.deleteAll();
        this.pojoService = pojoService;
        this.pojoRepository = pojoRepository;
        this.attributeRepository = attributeRepository;
        this.asmWrapperService = asmWrapperService;
        var t = loadClasses(new File("/Users/mpetersen/Desktop/pojo-malte/src/test/java/de/fh/kiel/advancedjava/pojomodel/exampleData/jars/Classes.jar"));
        var list = t.stream().map((p)->pojoService.createPojoFromPojoInfo(p)).collect(Collectors.toList());
        for (Pojo pojo:list
             ) {
           var exist  = pojoRepository.findById(pojo.getCompletePath());
            if(exist.isPresent() && exist.get().isEmptyHull() || exist.isEmpty()) {
                pojoRepository.deleteById(pojo.getCompletePath());
                pojoRepository.save(pojo);
            }
        }

      var all =   pojoRepository.findAll();
        System.out.println(all);
    }
    public  List<Class<?>> extractPojos(byte[] pojosAsJar)  {
        return Collections.emptyList();
    }

    public List<Pojo> getAllPojos(){
        return pojoRepository.findAll();
    }

    public List<Pojo> importPojos(List<Pojo> pojos){
        pojoRepository.deleteAll();
        attributeRepository.deleteAll();

        pojoRepository.saveAll(pojos);
        return pojoRepository.findAll();
    }
    List<PojoInfo> loadClasses(File jarFile) throws IOException {
        var classes = new ArrayList<PojoInfo>();
        var jar = new JarFile(jarFile);
        Stream<JarEntry> str = jar.stream();
        str.forEach(z -> readJar(jar, z, classes));
        jar.close();
        return classes;
    }
    void readJar(JarFile jar, JarEntry entry, List<PojoInfo> classes) {
        var name = entry.getName();
        try (var inputStream = jar.getInputStream(entry)){
            if (name.endsWith(".class")) {
                byte[] bytes = IOUtils.toByteArray(inputStream);
                var pojoInfo = asmWrapperService.read(bytes);
                classes.add(pojoInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
