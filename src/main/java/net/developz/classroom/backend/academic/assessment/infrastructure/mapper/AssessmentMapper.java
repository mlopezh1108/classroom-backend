package net.developz.classroom.backend.academic.assessment.infrastructure.mapper;

import net.developz.classroom.backend.academic.assessment.application.dto.ExamAttemptDTO;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttempt;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssessmentMapper {

    ExamAttemptDTO toDTO(ExamAttempt examAttempt);

    ExamAttempt toEntity(
            net.developz.classroom.backend.academic.assessment.application.dto.StartExamAttemptRequest request);
}
