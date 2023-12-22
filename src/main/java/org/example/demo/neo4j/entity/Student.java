package org.example.demo.neo4j.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Person
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-12-22 10:55:28
 */
@Data
@NoArgsConstructor
@Node("student")
public class Student implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    // type 时关系表的名称，OUTGOING 表示指向别人，代表从当前类指向属性所属的类
    @Relationship(type = "学校关系", direction = Relationship.Direction.OUTGOING)
    private Set<StudentRelationship> outStudentRelationships = new HashSet<>();

    public Student(String name) {
        this.name = name;
    }
}
