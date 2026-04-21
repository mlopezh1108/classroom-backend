package net.developz.classroom.backend.catalog.subject.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.shared.infrastructure.persistence.entities.BaseEntity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "subject_id", length = 26))
public class SubjectEntity extends BaseEntity {

    @Column(name = "subject_code", length = 7, unique = true)
    private String subjectCode;

    @Column(name = "subject_name", length = 100)
    private String subjectName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "subject_names", columnDefinition = "jsonb")
    private Map<String, String> subjectNames = new HashMap<>();

    @Column(name = "nivel")
    private Integer level;
}



