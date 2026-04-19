package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.enums.AttemptStatus;

import net.developz.classroom.backend.shared.infrastructure.persistence.entities.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exam_attempt")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "exam_attempt_id", length = 26))
public class ExamAttempt extends BaseEntity {

    @Column(name = "enrollment_id", length = 26)
    private String enrollmentId;

    @Column(name = "exam_id", length = 26)
    private String examId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "score")
    private Double score;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private AttemptStatus status = AttemptStatus.STARTED;

    @OneToMany(mappedBy = "examAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttemptAnswer> answers = new ArrayList<>();
}
