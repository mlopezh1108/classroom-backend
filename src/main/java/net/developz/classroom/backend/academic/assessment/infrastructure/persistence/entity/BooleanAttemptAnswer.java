package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity;

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
public class BooleanAttemptAnswer extends AttemptAnswer {

    @Column(name = "response_value")
    private Boolean responseValue;
}





