package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.model.PojoInfo;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.apache.commons.io.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PojosService {
    private final PojoRepository pojoRepository;

    private final AttributeRepository attributeRepository;
    @Autowired
    private ASMFacadeService asmFacadeService;
    private final PojoService pojoService;
    private final PojoFacadeService pojoFacadeService;



    PojosService(PojoService pojoService, PojoRepository pojoRepository, AttributeRepository attributeRepository, ASMFacadeService asmFacadeService, PojoFacadeService pojoFacadeService) throws IOException {
        this.pojoService = pojoService;
        this.pojoRepository = pojoRepository;
        this.attributeRepository = attributeRepository;
        this.asmFacadeService = asmFacadeService;
        this.pojoFacadeService = pojoFacadeService;
    }
    public List<Pojo> uploadJarAsMultipartAndCreatePojos(MultipartFile jarFile) throws IOException {
        var file = new File("temp.jar");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(jarFile.getBytes());
        }catch (IOException e){
            throw e;
        }
        return addToDB(file);
    }
    public List<Pojo> uploadJarAsBase64AndCreatePojos(byte[] input) throws IOException {
        var file = new File("temp.jar");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(input);
        }catch (IOException e){
            throw e;
        }
        return addToDB(file);
    }
    public List<Pojo> addToDB(File file) throws IOException {
        var t = loadClasses(file);
        var list = t.stream().map(pojoFacadeService::createPojo).collect(Collectors.toList());
        for (Pojo pojo:list
        ) {
            var exist  = pojoRepository.findById(pojo.getCompletePath());
            if(exist.isPresent() && exist.get().isEmptyHull() || exist.isEmpty()) {
                pojoRepository.deleteById(pojo.getCompletePath());
                pojoRepository.save(pojo);
            }
        }
        return pojoRepository.findAll();
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
        pojos.forEach(this::trySave);
        //pojoRepository.saveAll(pojos);
        return pojoRepository.findAll();
    }
    private void trySave(Pojo pojo){
          var test =pojoRepository.findById(pojo.getCompletePath());
                  if(test.isPresent()){
                      test.get().setEmptyHull(pojo.isEmptyHull());
                      test.get().setAttributes(pojo.getAttributes().stream().map(this::getAttribute).collect(Collectors.toSet()));
                      test.get().setInterfaces(pojo.getInterfaces());
                      test.get().setParentClass(pojo.getParentClass());
                      pojo = test.get();
                  }
                  pojo.setAttributes(pojo.getAttributes().stream().map(this::getAttribute).collect(Collectors.toSet()));
                  pojo.setAttributes(null);
                  pojoRepository.save(pojo);
    }
    private Attribute getAttribute(Attribute attribute){
      Attribute attr = attributeRepository.findById(attribute.getId()).orElse(attribute);
     attr.setClazz( pojoRepository.findById( attr.getClazz().getCompletePath()).orElse(attr.getClazz()));
      return attr;
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
                var pojoInfo = asmFacadeService.read(bytes);
                classes.add(pojoInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
