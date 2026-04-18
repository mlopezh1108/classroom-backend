package net.developz.classroom.backend.academic.course.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import net.developz.classroom.backend.academic.course.application.dto.CourseResponse;
import net.developz.classroom.backend.academic.course.application.dto.CreateCourseRequest;
import net.developz.classroom.backend.academic.course.application.dto.UpdateCourseRequest;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseResponse toResponse(Course course);
    Course toEntity(CreateCourseRequest request);
    void updateEntityFromRequest(UpdateCourseRequest request, @MappingTarget Course course);
}
