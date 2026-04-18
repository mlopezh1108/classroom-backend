package net.developz.classroom.backend.iam.access.infrastructure.persistence.entity;

import net.developz.classroom.backend.iam.user.infrastructure.persistence.entity.Person;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.shared.infrastructure.persistence.entities.BaseEntity;

@Entity
@Table(name = "person_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "person_permission_id", length = 26))
public class PersonPermission extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "notes", length = 255)
    private String notes;
}



