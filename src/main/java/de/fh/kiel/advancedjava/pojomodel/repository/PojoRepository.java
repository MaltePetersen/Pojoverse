package de.fh.kiel.advancedjava.pojomodel.repository;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;


public interface PojoRepository extends Neo4jRepository<Pojo, String> {

    public List<Pojo> findAllByClassName(String className);

    public List<Pojo> findAllByaPackage_Id(String id);

    public List<Pojo> findAllByParentClass_CompletePath(String completePath);
}
