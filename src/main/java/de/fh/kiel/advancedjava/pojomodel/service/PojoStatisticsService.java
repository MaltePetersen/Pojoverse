package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.PojoStatistics;
import de.fh.kiel.advancedjava.pojomodel.exception.PojoDoesNotExist;
import de.fh.kiel.advancedjava.pojomodel.repository.AttributeRepository;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

@Service
public class PojoStatisticsService {


    private final PojoRepository pojoRepository;
    private final AttributeRepository attributeRepository;

    public PojoStatisticsService(PojoRepository pojoRepository, AttributeRepository attributeRepository) {
        this.pojoRepository = pojoRepository;
        this.attributeRepository = attributeRepository;
    }

    public PojoStatistics getStatistics(String pojoName){
       var pojo = pojoRepository.findById(pojoName).orElseThrow(() -> new PojoDoesNotExist(pojoName));

       var numberOfClassesWithTheSameName = pojoRepository.findAllByClassName(pojo.getClassName()).size();
       var numberOfClassesInTheSamePackage = pojoRepository.findAllByaPackage_Id(pojo.getAPackage().getId()).size();
       var numberOfAttributesWithDatatype = attributeRepository.findAllByClazz_CompletePath(pojo.getCompletePath()).size();
       var numberOfDirectSubClasses = pojoRepository.findAllByParentClass_CompletePath(pojo.getCompletePath()).size();
       return PojoStatistics.builder()
               .classname(pojo.getClassName())
               .packageName(pojo.getAPackage().getName())
               .numberOfAttributes(pojo.getAttributes() != null ? pojo.getAttributes().size(): 0)
               .parentClassName(pojo.getParentClass() != null ? pojo.getParentClass().getClassName() : null)
               .implementedInterfaces(pojo.getInterfaces())
               .numberOfClassesWithTheSameName(numberOfClassesWithTheSameName)
               .numberOfClassesInTheSamePackage(numberOfClassesInTheSamePackage)
               .numberOfAttributesThatHaveTheCorrespondingDataType(numberOfAttributesWithDatatype)
               .numberOfDirectSubClasses(numberOfDirectSubClasses)
               .build();


   }
}
