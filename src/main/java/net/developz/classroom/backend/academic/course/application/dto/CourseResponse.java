package net.developz.classroom.backend.academic.course.application.dto;

import net.developz.classroom.backend.academic.course.domain.model.enums.CourseStatus;

public record CourseResponse(
    String id,
    String courseCode,
    String teacherName,
    String groupCode,
    String subjectName,
    String periodCode,
    CourseStatus status
) {}
