package net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;
import net.developz.classroom.backend.shared.infrastructure.persistence.entities.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "enrollment", indexes = {
        @Index(name = "idx_enrollment_student_course", columnList = "student_id, course_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "enrollment_id", length = 26))
public class Enrollment extends BaseEntity {

    @Column(name = "grade", precision = 5, scale = 2)
    private BigDecimal grade;

    @Column(name = "student_id", length = 26)
    private String studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

}
