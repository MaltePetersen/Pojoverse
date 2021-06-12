package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.JavaFileDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.IsEmptyHullException;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExistException;
import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * The general design idea behind this service is to deliberately not use a lot the templateing features tyhmeleaf offers
 * The reason for that is because because templating a txt in thymeleaf is badly documented and the syntax is bad to read.
 * This is in stark contrast to the html templating in thymeleaf which is greatly documented.
 * In a real world project with a lot of template which might change a lot or which might even be written by the customer
 * I would use for example velocity for creating txt files. In this example it is just one file we need which structure will not change a lot
 * therefore a solution where the String generation is more focused in java is accptable to me and it has the benefit that we do not need to add
 * another lib to the project.
 * Note: Interfaces is just a one string because interfaces are only used at one place in the template therefore we can autogenearate the whole string
 * which will be inserted there instead of using thymeleaf
 */
@Service
public class JavaFileService {


    private static final String JAVA_TEMPLATE = "javaFileTemplate.txt";

    private static final String JAVA_LANG_OBJECT = "java.lang.Object";

    private static final String JAVA_LANG = "java.lang";

    private final TemplateEngine textTemplateEngine;
    private final PojoRepository pojoRepository;

    public JavaFileService(TemplateEngine textTemplateEngine, PojoRepository pojoRepository) {
        this.textTemplateEngine = textTemplateEngine;
        this.pojoRepository = pojoRepository;
    }

    public String createJavaFile(String pojoId) {

        var pojo = pojoRepository.findById(pojoId).orElseThrow(() -> new PojoDoesNotExistException(pojoId));

        if (pojo.isEmptyHull())
            throw new IsEmptyHullException(pojoId);

        var javaFile = new JavaFileDTO(pojo.getClassName(), createPackage(pojo.getAPackage().getId()));

        addInterfaces(pojo, javaFile);

        addParentClass(pojo, javaFile);

        addAttributes(pojo, javaFile);

        final var ctx = new Context();
        ctx.setVariable("pojo", javaFile);

        return this.textTemplateEngine.process(JAVA_TEMPLATE, ctx);
    }

    private void addInterfaces(Pojo pojo, JavaFileDTO javaFile) {
        if (!pojo.getInterfaces().isEmpty()) {
            javaFile.setInterfaces(String.join(",", pojo.getInterfaces()));
        }
    }

    private void addParentClass(Pojo pojo, JavaFileDTO javaFile) {
        if (pojo.getParentClass() != null && !pojo.getParentClass().getCompletePath().equals(JAVA_LANG_OBJECT)) {
            javaFile.setSuperClassName(pojo.getParentClass().getClassName());

            if (!pojo.getParentClass().getCompletePath().contains(JAVA_LANG))
                javaFile.getImports().add(createImport(pojo.getParentClass().getCompletePath()));

        }
    }

    private void addAttributes(Pojo pojo, JavaFileDTO javaFileDTO) {
        for (Attribute attribute :
                pojo.getAttributes()) {

            if (classNeedsToBeImported(attribute.getClazz()))
                javaFileDTO.getImports().add(createImport(attribute.getClazz().getCompletePath()));

            if (attribute.getGenericType() != null)
                addGenericAttributeImport(attribute, javaFileDTO);

            else
                javaFileDTO.getAttributes().add(createAttribute(attribute));
        }
    }

    private void addGenericAttributeImport(Attribute attribute, JavaFileDTO javaFileDTO) {
        if (classNeedsToBeImported(attribute.getGenericType()))
            javaFileDTO.getImports().add(createImport(attribute.getGenericType().getCompletePath()));

        javaFileDTO.getAttributes().add(createGenericAttribute(attribute));
    }

    private boolean classNeedsToBeImported(Pojo pojo) {
        return !pojo.getCompletePath().contains(JAVA_LANG);
    }

    private String createAttribute(Attribute attribute) {
        return attribute.getAccessModifier() + " " + attribute.getClazz().getClassName() + " " + attribute.getName() + ";";
    }

    private String createGenericAttribute(Attribute attribute) {
        return attribute.getAccessModifier() + " List<" + attribute.getGenericType().getClassName() + "> " + attribute.getName() + ";";
    }

    private String createImport(String completePath) {
        return "import " + completePath + ";";
    }

    private String createPackage(String packageName) {
        return "package " + packageName + ";";
    }


}
