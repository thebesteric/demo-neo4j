package org.example.demo.neo4j.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

/**
 * PersonRelationship
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-12-22 11:13:03
 */
@Data
@RelationshipProperties
public class StudentRelationship {

    @RelationshipId
    private Long id;

    @TargetNode
    private Student target;

    @Property
    private String relation;

}
