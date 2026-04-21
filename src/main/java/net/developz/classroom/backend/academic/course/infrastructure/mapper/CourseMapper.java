package net.developz.classroom.backend.academic.course.infrastructure.mapper;

import net.developz.classroom.backend.academic.course.application.dto.CourseResponse;
import net.developz.classroom.backend.academic.course.application.dto.CreateCourseRequest;
import net.developz.classroom.backend.academic.course.application.dto.UpdateCourseRequest;
import net.developz.classroom.backend.academic.course.domain.model.Course;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.CourseDetailsProjection;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    // Entity/Projection <-> Domain
    Course toModel(CourseEntity entity);

    Course toModel(CourseDetailsProjection projection);

    CourseEntity toEntity(Course model);

    // Domain <-> DTO
    CourseResponse toResponse(Course model);

    // Request -> Domain
    Course toModel(CreateCourseRequest request);

    void updateModelFromRequest(UpdateCourseRequest request, @MappingTarget Course model);
}
