package de.fh.kiel.advancedjava.pojomodel.repository;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface PojoRepository extends Neo4jRepository<Pojo, String> {
}
