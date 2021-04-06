package de.fh.kiel.advancedjava.pojomodel.repository;


import de.fh.kiel.advancedjava.pojomodel.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);
    List<Person> findByTeammatesName(String name);

}