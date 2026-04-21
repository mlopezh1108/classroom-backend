package net.developz.classroom.backend.academic.assessment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class AttemptAnswer extends BaseModel {
    private ExamAttempt examAttempt;
    private String questionId;
    private BigDecimal score;
}
