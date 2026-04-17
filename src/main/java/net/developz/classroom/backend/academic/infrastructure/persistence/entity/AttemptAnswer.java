package net.developz.classroom.backend.academic.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.shared.infrastructure.persistence.entities.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "attempt_answer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "answer_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "answer_id", length = 26))
public abstract class AttemptAnswer extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
    private ExamAttempt examAttempt;

    @Column(name = "question_id", length = 26, nullable = false)
    private String questionId;

    @Column(name = "score", precision = 5, scale = 2)
    private BigDecimal score;
}
