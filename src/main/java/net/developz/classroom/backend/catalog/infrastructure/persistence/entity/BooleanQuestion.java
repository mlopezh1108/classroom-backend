package net.developz.classroom.backend.catalog.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("BOOLEAN")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BooleanQuestion extends Question {

    @Column(name = "correct_answer")
    private Boolean correctAnswer;
}
