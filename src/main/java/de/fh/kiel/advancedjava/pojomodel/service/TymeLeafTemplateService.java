package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.ClassDTO;
import de.fh.kiel.advancedjava.pojomodel.exception.IsEmptyHull;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashSet;


@Service
public class TymeLeafTemplateService {

    private static final String JAVA_TEMPLATE = "javaFileTemplate.txt";

    private static final String JAVA_TEMPLATE_OPTIMIZIED = "javaFileTemplateOptimizied.txt";


    private final String JAVA_LANG_OBJECT = "java.lang.Object";

    private final String JAVA_LANG = "java.lang";

    private final TemplateEngine textTemplateEngine;
    private final PojoRepository pojoRepository;

    public TymeLeafTemplateService(TemplateEngine textTemplateEngine, PojoRepository pojoRepository) {
        this.textTemplateEngine = textTemplateEngine;
        this.pojoRepository = pojoRepository;
    }

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

        var classDto = ClassDTO.builder()
                .packageName(createPackage(pojo.getAPackage().getId()))
                .className(pojo.getClassName())
                .build();
        var imports = new HashSet<String>();
        var attributes = new HashSet<String>();

        if (!pojo.getInterfaces().isEmpty()) {
            classDto.setInterfaces(String.join(",", pojo.getInterfaces()));
        }

        if (pojo.getParentClass() != null && !pojo.getParentClass().getCompletePath().equals(JAVA_LANG_OBJECT)) {
            classDto.setSuperClassName(pojo.getParentClass().getClassName());

            if (!pojo.getParentClass().getCompletePath().contains(JAVA_LANG))
                imports.add(createImport(pojo.getParentClass().getCompletePath()));

        }
        for (Attribute attribute :
                pojo.getAttributes()) {
            if (!attribute.getClazz().getCompletePath().contains(JAVA_LANG))
                imports.add(createImport(attribute.getClazz().getCompletePath()));
            if (attribute.getGenericType() != null) {
                if (!attribute.getGenericType().getCompletePath().contains(JAVA_LANG))
                    imports.add(createImport(attribute.getGenericType().getCompletePath()));
                attributes.add(createGenericAttribute(attribute));
            } else
                attributes.add(createAttribute(attribute));


        }

        classDto.setAttributes(attributes);
        classDto.setImports(imports);

        final var ctx = new Context();
        ctx.setVariable("pojo", classDto);

        return this.textTemplateEngine.process(JAVA_TEMPLATE_OPTIMIZIED, ctx);
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
