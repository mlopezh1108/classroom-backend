package net.developz.classroom.backend.academic.infrastructure.persistence.entities;
 
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.academic.infrastructure.persistence.entities.enums.AttemptStatus;
import net.developz.classroom.backend.infrastructure.persistence.entities.BaseEntity;
 
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
 
@Entity
@Table(name = "exam_attempt")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "attempt_id", length = 26))
public class ExamAttempt extends BaseEntity {
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;
 
    @Column(name = "exam_id", length = 26, nullable = false)
    private String examId;
 
    @Column(name = "started_at")
    private LocalDateTime startedAt;
 
    @Column(name = "finished_at")
    private LocalDateTime finishedAt;
 
    @Column(name = "score", precision = 5, scale = 2)
    private BigDecimal score;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private AttemptStatus status = AttemptStatus.STARTED;
 
    @OneToMany(mappedBy = "examAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttemptAnswer> answers = new ArrayList<>();
}
