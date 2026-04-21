package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MultipleChoiceAttemptAnswerEntity extends AttemptAnswerEntity {

    @Column(name = "selected_option_id", length = 26)
    private String selectedOptionId;
}



