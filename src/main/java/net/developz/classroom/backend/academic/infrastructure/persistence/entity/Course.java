package net.developz.classroom.backend.academic.infrastructure.persistence.entity;

import jakarta.persistence.*;
import net.developz.classroom.backend.academic.infrastructure.persistence.entity.enums.CourseStatus;
import net.developz.classroom.backend.shared.infrastructure.persistence.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course", indexes = @Index(name = "idx_course_code", columnList = "course_code"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "course_id", length = 26))
public class Course extends BaseEntity {

    @Column(name = "course_code", length = 50)
    private String courseCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private CourseStatus status;

    @Column(name = "teacher_id", length = 26)
    private String teacherId;

    @Column(name = "group_id", length = 26)
    private String groupId;

    @Column(name = "subject_id", length = 26)
    private String subjectId;

    @Column(name = "period_id", length = 26)
    private String periodId;

}
