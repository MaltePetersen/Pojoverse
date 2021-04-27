package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.exception.IsEmptyHull;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
public class TymeLeafTemplateService {

    private static final String JAVATEMPLATE = "text.txt";


    private TemplateEngine textTemplateEngine;
    private final PojoRepository pojoRepository;

    public TymeLeafTemplateService(TemplateEngine textTemplateEngine, PojoRepository pojoRepository) {
        this.textTemplateEngine = textTemplateEngine;
        this.pojoRepository = pojoRepository;
    }

    public String createJavaFile(String pojoId){
        Pojo pojo = pojoRepository.findById(pojoId).orElseThrow(() -> new PojoDoesNotExist(pojoId));
        if(pojo.isEmptyHull())
            throw new IsEmptyHull(pojoId);
        final Context ctx = new Context();
        ctx.setVariable("pojo", pojo);

        return  this.textTemplateEngine.process(JAVATEMPLATE, ctx);
    }
}
