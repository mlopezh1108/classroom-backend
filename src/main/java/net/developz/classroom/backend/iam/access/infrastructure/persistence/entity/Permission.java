package net.developz.classroom.backend.iam.access.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import net.developz.classroom.backend.shared.infrastructure.persistence.entities.BaseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "permissions")
@AttributeOverride(name = "id", column = @Column(name = "permission_id", length = 26))
public class Permission extends BaseEntity {

    @Column(name = "permission_name", nullable = false, length = 100, unique = true)
    private String permissionName;

    @Column(name = "description", length = 255)
    private String description;
}



