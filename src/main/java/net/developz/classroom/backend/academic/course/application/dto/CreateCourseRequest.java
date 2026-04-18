package net.developz.classroom.backend.academic.course.application.dto;

import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.enums.CourseStatus;

public record CreateCourseRequest(
    String courseCode,
    String teacherId,
    String groupId,
    String subjectId,
    String periodId,
    CourseStatus status
) {}
