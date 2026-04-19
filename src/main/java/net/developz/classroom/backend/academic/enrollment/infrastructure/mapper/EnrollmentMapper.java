package net.developz.classroom.backend.academic.enrollment.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import net.developz.classroom.backend.academic.enrollment.application.dto.EnrollmentDTO;
import net.developz.classroom.backend.academic.enrollment.application.dto.EnrollStudentRequest;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    
    @Mapping(target = "courseId", source = "course.id")
    EnrollmentDTO toDTO(Enrollment enrollment);

    @Mapping(target = "course.id", source = "courseId")
    Enrollment toEntity(EnrollStudentRequest request);
}
