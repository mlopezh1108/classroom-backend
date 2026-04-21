package net.developz.classroom.backend.academic.enrollment.infrastructure.mapper;

import net.developz.classroom.backend.academic.course.infrastructure.mapper.CourseMapper;
import net.developz.classroom.backend.academic.enrollment.application.dto.EnrollmentDTO;
import net.developz.classroom.backend.academic.enrollment.application.dto.EnrollStudentRequest;
import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {CourseMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnrollmentMapper {
    
    // Entity/Projection <-> Domain
    Enrollment toModel(EnrollmentEntity entity);
    @Mapping(target = "course.id", source = "courseId")
    Enrollment toModel(net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentDetailsProjection projection);
    EnrollmentEntity toEntity(Enrollment model);

    // Domain <-> DTO
    EnrollmentDTO toDto(Enrollment model);
    
    // Request -> Domain
    @Mapping(target = "course.id", source = "courseId")
    Enrollment toModel(EnrollStudentRequest request);
}
