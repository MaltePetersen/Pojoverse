package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.JavaFileDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.IsEmptyHull;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashSet;
import java.util.Set;


@Service
public class JavaFileService {

    private static final String JAVA_TEMPLATE = "javaFileTemplate.txt";

    private static final String JAVA_TEMPLATE_OPTIMIZIED = "javaFileTemplateOptimizied.txt";

    private final String JAVA_LANG_OBJECT = "java.lang.Object";

    private final String JAVA_LANG = "java.lang";

    private final TemplateEngine textTemplateEngine;
    private final PojoRepository pojoRepository;

    public JavaFileService(TemplateEngine textTemplateEngine, PojoRepository pojoRepository) {
        this.textTemplateEngine = textTemplateEngine;
        this.pojoRepository = pojoRepository;
    }
    /**
     * Method to generate a JavaFile from a pojo
     * @deprecated
     * This method is no longer used because the direct use
     * of a template leads to messy code.
     * This method would normally be deleted but
     * is still here to show the reviewers,
     * why the optimisied version uses mostly java
     * instead of tymeleaf to generate the content of
     * the templates. If you have a look at the JAVA_TEMPLATE
     * It is easy to see how hard to maintain such a template would be
     * in the long run.
     *
     */
    @Deprecated
    public String createJavaFile(String pojoId) {
        var pojo = pojoRepository.findById(pojoId).orElseThrow(() -> new PojoDoesNotExist(pojoId));
        if (pojo.isEmptyHull())
            throw new IsEmptyHull(pojoId);
        final var ctx = new Context();
        ctx.setVariable("pojo", pojo);

        return this.textTemplateEngine.process(JAVA_TEMPLATE, ctx);
    }

    public String createOptimziedJavaFile(String pojoId) {

        var pojo = pojoRepository.findById(pojoId).orElseThrow(() -> new PojoDoesNotExist(pojoId));

        if (pojo.isEmptyHull())
            throw new IsEmptyHull(pojoId);

        var javaFile = new JavaFileDTO(pojo.getClassName(), createPackage(pojo.getAPackage().getId()));

        addInterfaces(pojo, javaFile);

        addParentClass(pojo, javaFile);

        addAttributes(pojo, javaFile);

        final var ctx = new Context();
        ctx.setVariable("pojo", javaFile);

        return this.textTemplateEngine.process(JAVA_TEMPLATE_OPTIMIZIED, ctx);
    }

    private void addInterfaces(Pojo pojo, JavaFileDTO javaFile){
        if (!pojo.getInterfaces().isEmpty()) {
            javaFile.setInterfaces(String.join(",", pojo.getInterfaces()));
        }
    }

    private void addParentClass(Pojo pojo, JavaFileDTO javaFile){
        if (pojo.getParentClass() != null && !pojo.getParentClass().getCompletePath().equals(JAVA_LANG_OBJECT)) {
            javaFile.setSuperClassName(pojo.getParentClass().getClassName());

            if (!pojo.getParentClass().getCompletePath().contains(JAVA_LANG))
                javaFile.getImports().add(createImport(pojo.getParentClass().getCompletePath()));

        }
    }

    private void addAttributes(Pojo pojo, JavaFileDTO javaFileDTO){
        for (Attribute attribute :
                pojo.getAttributes()) {

            if ( classNeedsToBeImported(attribute.getClazz()) )
                javaFileDTO.getImports().add(createImport(attribute.getClazz().getCompletePath()));

            if ( attribute.getGenericType() != null)
                addGenericAttributeImport(attribute, javaFileDTO);

             else
                javaFileDTO.getAttributes().add(createAttribute(attribute));
        }
    }

    private void addGenericAttributeImport(Attribute attribute, JavaFileDTO javaFileDTO){
        if ( classNeedsToBeImported(attribute.getGenericType()) )
            javaFileDTO.getImports().add(createImport(attribute.getGenericType().getCompletePath()));

        javaFileDTO.getAttributes().add(createGenericAttribute(attribute));
    }

    private boolean classNeedsToBeImported(Pojo pojo){
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
