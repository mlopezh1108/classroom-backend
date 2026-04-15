package net.developz.classroom.backend.catalog.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.infrastructure.persistence.entities.BaseEntity;

@Entity
@Table(name = "day")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "day_id", length = 26))
public class Day extends BaseEntity {

    @Column(name = "day_name", length = 10)
    private String dayName;

}
