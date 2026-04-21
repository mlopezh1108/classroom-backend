package net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity;

import java.math.BigDecimal;

public interface EnrollmentDetailsProjection {
    String getId();
    BigDecimal getGrade();
    String getStudentId();
    String getCourseId();
    String getStudentName();
    String getCourseCode();
    String getSubjectName();
}
