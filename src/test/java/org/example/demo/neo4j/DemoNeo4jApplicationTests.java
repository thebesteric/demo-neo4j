package org.example.demo.neo4j;

import org.example.demo.neo4j.dao.StudentRelationshipRepository;
import org.example.demo.neo4j.dao.StudentRepository;
import org.example.demo.neo4j.entity.Student;
import org.example.demo.neo4j.entity.StudentRelationship;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
class DemoNeo4jApplicationTests {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentRelationshipRepository studentRelationshipRepository;

    void delete() {

    }

    @Test
    void getNode() {
        Optional<Student> optional = studentRepository.findById(38L);
        System.out.println(optional.orElse(null));
    }

    @Test
    void deleteNode() {
        studentRepository.deleteById(38L);
    }

    @Test
    void createNode() {
        Student person1 = new Student("张三");
        Student person2 = new Student("李四");
        System.out.println(studentRepository.saveAll(Arrays.asList(person1, person2)));
    }

    @Test
    void createrelation() {
        Student zs = studentRepository.findByName("张三");
        Student ls = studentRepository.findByName("李四");
        Student zl = studentRepository.findByName("赵六");

        // StudentRelationship zsOutRelationship = new StudentRelationship();
        // zsOutRelationship.setTarget(ls);
        // zsOutRelationship.setRelation("同班同学");
        // zs.getOutStudentRelationships().add(zsOutRelationship);

        StudentRelationship zsOutRelationship = new StudentRelationship();
        zsOutRelationship.setTarget(zl);
        zsOutRelationship.setRelation("学长");
        zs.getOutStudentRelationships().add(zsOutRelationship);

        studentRepository.save(zs);
    }

    @Test
    void createRelationByCQL() {
        studentRepository.createRelation("张三", "同班同学", "李四");
        studentRepository.createRelation("李四", "同班同学", "张三");

        studentRepository.createRelation("小明", "学长", "张三");
        studentRepository.createRelation("小明", "学长", "李四");

        studentRepository.createRelation("小红", "学长", "张三");
        studentRepository.createRelation("小红", "学长", "李四");

        studentRepository.createRelation("小明", "同学", "小红");
        studentRepository.createRelation("小红", "同学", "小明");
    }

    @Test
    void deleteRelationByCQL() {
        studentRepository.deleteRelation("小红", "同学", "小明");
        studentRepository.deleteRelation("小明", "同学", "小红");
    }

    @Test
    void deleteRelationAllByCQL() {
        studentRepository.deleteRelation();
    }

    @Test
    void createNodeByCQL() {
        studentRepository.createStudent("张三");
        studentRepository.createStudent("李四");
        studentRepository.createStudent("王五");
        studentRepository.createStudent("赵六");
        studentRepository.createStudent("小明");
        studentRepository.createStudent("小红");
    }
}
