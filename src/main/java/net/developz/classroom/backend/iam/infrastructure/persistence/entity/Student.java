package net.developz.classroom.backend.iam.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.shared.infrastructure.persistence.entities.BaseEntity;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "student_id", length = 26))
public class Student extends BaseEntity {

    @Column(name = "student_code", unique = true)
    private Integer studentCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}
