package net.developz.classroom.backend.iam.access.infrastructure.persistence.entity;



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
public class PersonPermissionEntity extends BaseEntity {

    @Column(name = "person_id", length = 26, nullable = false)
    private String personId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private PermissionEntity permission;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "notes", length = 255)
    private String notes;
}



