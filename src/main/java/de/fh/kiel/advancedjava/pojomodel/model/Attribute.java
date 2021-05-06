package de.fh.kiel.advancedjava.pojomodel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.*;


@Node
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attribute {

    @Id
    private String id;
    @Version
    private Long version;
    private String name;
    private String accessModifier;
    //isOnlySet if the class is of type java.util.list
    private Pojo genericType;

    @Relationship("DeclaringClass")
    private Pojo clazz;

}
