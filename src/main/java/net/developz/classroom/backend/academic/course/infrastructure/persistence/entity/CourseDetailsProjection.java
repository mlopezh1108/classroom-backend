package net.developz.classroom.backend.academic.course.infrastructure.persistence.entity;

import net.developz.classroom.backend.academic.course.domain.model.enums.CourseStatus;

public interface CourseDetailsProjection {
    String getId();
    String getCourseCode();
    CourseStatus getStatus();
    String getTeacherId();
    String getGroupId();
    String getSubjectId();
    String getPeriodId();
    String getTeacherName();
    String getGroupCode();
    String getSubjectName();
    String getPeriodCode();
}
