package org.example.demo.neo4j.dao;

import org.example.demo.neo4j.entity.Student;
import org.example.demo.neo4j.entity.StudentRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PersonRepository
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-12-22 10:53:57
 */
@Repository
public interface StudentRepository extends Neo4jRepository<Student, Long> {

    Student findByName(String name);


    @Query("match (n:student {name: $from}), (m:student {name: $to}) " +
            "create (n)-[r:`学校关系` {relation:$relation}]->(m)")
    void createRelation(String from, String relation, String to);

    @Query("match ()-[r:`学校关系`]->() " +
            "delete r")
    void deleteRelation();

    @Query("match (n:student {name: $from})-[r:`学校关系` {relation: $relation}]->(m:student {name: $to}) delete r " +
            "delete r")
    void deleteRelation(String from, String relation, String to);

    @Query("create (n:student1 {name: $name}) return n")
    Student createStudent(String name);
}
