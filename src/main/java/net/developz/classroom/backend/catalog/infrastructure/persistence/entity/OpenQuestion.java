package net.developz.classroom.backend.catalog.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("OPEN")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OpenQuestion extends Question {
    // Open questions might not need extra fields on top of base text
}
