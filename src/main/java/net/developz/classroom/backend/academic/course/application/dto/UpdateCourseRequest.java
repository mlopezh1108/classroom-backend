package net.developz.classroom.backend.academic.course.application.dto;

import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.enums.CourseStatus;

public record UpdateCourseRequest(
    String teacherId,
    CourseStatus status
) {}
