package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.exception.CouldNotReadJarException;
import de.fh.kiel.advancedjava.pojomodel.model.PojoInfo;
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

@Service
public class JarReaderService {

    private final ASMFacadeService asmFacadeService;

    public JarReaderService(ASMFacadeService asmFacadeService) {
        this.asmFacadeService = asmFacadeService;
    }

    public List<PojoInfo> read(byte[] input) {
        try {
            var file = new File("temp.jar");
            try (OutputStream os = new FileOutputStream(file)) {
                os.write(input);
            }
            return createPojoInfos(file);
        } catch (IOException e) {
            throw new CouldNotReadJarException();
        }
    }

    private List<PojoInfo> createPojoInfos(File jarFile) {
        var classes = new ArrayList<PojoInfo>();
        try(var jar = new JarFile(jarFile)) {
            jar.stream().forEach(entry -> readEntry(jar, entry, classes));
            return classes;
        } catch (IOException e) {
            throw new CouldNotReadJarException();
        }
    }

    private void readEntry(JarFile jar, JarEntry entry, List<PojoInfo> pojoInfos) {
        var name = entry.getName();
        try (var inputStream = jar.getInputStream(entry)) {
            if (name.endsWith(".class")) {
                byte[] bytes = IOUtils.toByteArray(inputStream);
                var pojoInfo = asmFacadeService.read(bytes);
                pojoInfos.add(pojoInfo);
            }
        } catch (IOException e) {
            throw new CouldNotReadJarException();
        }
    }


}
