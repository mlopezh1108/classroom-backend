package net.developz.classroom.backend.academic.course.application.dto;

import net.developz.classroom.backend.academic.course.domain.model.enums.CourseStatus;

public record CreateCourseRequest(
    String courseCode,
    String teacherId,
    String groupId,
    String subjectId,
    String periodId,
    CourseStatus status
) {}
