package de.fh.kiel.advancedjava.pojomodel.repository;

import de.fh.kiel.advancedjava.pojomodel.model.Attribute;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface AttributeRepository extends Neo4jRepository<Attribute, String> {
    List<Attribute> findAllByClazz_CompletePath(String completePath);

    boolean existsByNameAndClazz_CompletePath(String name, String completePath);
}
