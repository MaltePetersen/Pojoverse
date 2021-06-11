package de.fh.kiel.advancedjava.pojomodel.repository;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;


public interface PojoRepository extends Neo4jRepository<Pojo, String> {

    List<Pojo> findAllByClassName(String className);

    List<Pojo> findAllByaPackage_Id(String id);

    List<Pojo> findAllByParentClass_CompletePath(String completePath);
}
