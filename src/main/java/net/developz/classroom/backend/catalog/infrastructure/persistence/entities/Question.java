package net.developz.classroom.backend.catalog.infrastructure.persistence.entities;
 
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entities.enums.QuestionType;
import net.developz.classroom.backend.infrastructure.persistence.entities.BaseEntity;
 
@Entity
@Table(name = "question")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "question_id", length = 26))
public abstract class Question extends BaseEntity {
 
    @Column(name = "text", length = 1000, nullable = false)
    private String text;
 
    @Column(name = "order_index")
    private Integer orderIndex;
 
    @Column(name = "points")
    private Double points;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", length = 20, nullable = false, insertable = false, updatable = false)
    private QuestionType questionType;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;
}
