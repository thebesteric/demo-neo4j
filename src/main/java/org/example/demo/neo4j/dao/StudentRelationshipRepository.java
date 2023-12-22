package org.example.demo.neo4j.dao;

import org.example.demo.neo4j.entity.StudentRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRelationshipRepository extends Neo4jRepository<StudentRelationship, Long> {
}
