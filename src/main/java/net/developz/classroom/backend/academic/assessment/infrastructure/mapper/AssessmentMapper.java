package net.developz.classroom.backend.academic.assessment.infrastructure.mapper;

import net.developz.classroom.backend.academic.assessment.application.dto.ExamAttemptDTO;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttempt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssessmentMapper {
    
    @Mapping(target = "enrollmentId", source = "enrollment.id")
    ExamAttemptDTO toDTO(ExamAttempt examAttempt);

    @Mapping(target = "enrollment.id", source = "enrollmentId")
    ExamAttempt toEntity(net.developz.classroom.backend.academic.assessment.application.dto.StartExamAttemptRequest request);
}
