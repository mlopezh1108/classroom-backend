package net.developz.classroom.backend.academic.enrollment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.developz.classroom.backend.academic.course.domain.model.Course;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Enrollment extends BaseModel {
    private BigDecimal grade;
    private String studentId;
    private Course course;
    private String studentName;
    private String courseCode;
    private String subjectName;
}
