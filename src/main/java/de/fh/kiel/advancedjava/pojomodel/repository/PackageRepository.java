package de.fh.kiel.advancedjava.pojomodel.repository;

import de.fh.kiel.advancedjava.pojomodel.model.Package;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PackageRepository extends Neo4jRepository<Package, String> {

}
