package net.developz.classroom.backend.catalog.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.infrastructure.persistence.entities.BaseEntity;

@Entity
@Table(name = "group_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "group_id", length = 26))
public class Group extends BaseEntity {

    @Column(name = "group_code", length = 5, unique = true)
    private String groupCode;

}
