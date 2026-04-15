package net.developz.classroom.backend.iam.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.infrastructure.persistence.entities.BaseEntity;

@Entity
@Table(name = "administrator")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "administrator_id", length = 26))
public class Administrator extends BaseEntity {

    @Column(name = "employee_number")
    private Integer employeeNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}

