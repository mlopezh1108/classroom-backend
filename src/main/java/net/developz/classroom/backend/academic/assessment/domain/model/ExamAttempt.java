package net.developz.classroom.backend.academic.assessment.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import net.developz.classroom.backend.academic.assessment.domain.model.enums.AttemptStatus;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExamAttempt extends BaseModel {
    private String enrollmentId;
    private String examId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double score;
    private AttemptStatus status;

    @Builder.Default
    private List<AttemptAnswer> answers = new ArrayList<>();
}
