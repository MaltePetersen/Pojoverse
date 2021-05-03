package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.exception.IsEmptyHull;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class TymeLeafTemplateService {

    private static final String JAVATEMPLATE = "javaFileTemplate.txt";


    private final TemplateEngine textTemplateEngine;
    private final PojoRepository pojoRepository;

    public TymeLeafTemplateService(TemplateEngine textTemplateEngine, PojoRepository pojoRepository) {
        this.textTemplateEngine = textTemplateEngine;
        this.pojoRepository = pojoRepository;
    }

    public String createJavaFile(String pojoId){
        var pojo = pojoRepository.findById(pojoId).orElseThrow(() -> new PojoDoesNotExist(pojoId));
        if(pojo.isEmptyHull())
            throw new IsEmptyHull(pojoId);
        final var ctx = new Context();
        ctx.setVariable("pojo", pojo);

        return  this.textTemplateEngine.process(JAVATEMPLATE, ctx);
    }
}
