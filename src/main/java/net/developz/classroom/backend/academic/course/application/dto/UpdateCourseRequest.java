package net.developz.classroom.backend.academic.course.application.dto;

import net.developz.classroom.backend.academic.course.domain.model.enums.CourseStatus;

public record UpdateCourseRequest(
    String teacherId,
    CourseStatus status
) {}
