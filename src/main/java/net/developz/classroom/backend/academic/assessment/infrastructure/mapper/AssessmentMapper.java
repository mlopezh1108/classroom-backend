package net.developz.classroom.backend.academic.assessment.infrastructure.mapper;

import net.developz.classroom.backend.academic.assessment.application.dto.ExamAttemptDTO;
import net.developz.classroom.backend.academic.assessment.application.dto.StartExamAttemptRequest;
import net.developz.classroom.backend.academic.assessment.domain.model.*;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssessmentMapper {
    
    // ExamAttempt Entity <-> Domain
    ExamAttempt toModel(ExamAttemptEntity entity);
    ExamAttemptEntity toEntity(ExamAttempt model);

    // ExamAttempt Domain <-> DTO
    ExamAttemptDTO toDto(ExamAttempt model);
    ExamAttempt toModel(StartExamAttemptRequest request);

    // Polymorphic AttemptAnswer Entity <-> Domain
    default AttemptAnswer toModel(AttemptAnswerEntity entity) {
        if (entity instanceof BooleanAttemptAnswerEntity b) return toModel(b);
        if (entity instanceof MultipleChoiceAttemptAnswerEntity mc) return toModel(mc);
        if (entity instanceof MatchingAttemptAnswerEntity m) return toModel(m);
        if (entity instanceof OpenAttemptAnswerEntity o) return toModel(o);
        return null;
    }

    default AttemptAnswerEntity toEntity(AttemptAnswer model) {
        if (model instanceof BooleanAttemptAnswer b) return toEntity(b);
        if (model instanceof MultipleChoiceAttemptAnswer mc) return toEntity(mc);
        if (model instanceof MatchingAttemptAnswer m) return toEntity(m);
        if (model instanceof OpenAttemptAnswer o) return toEntity(o);
        return null;
    }

    BooleanAttemptAnswer toModel(BooleanAttemptAnswerEntity entity);
    BooleanAttemptAnswerEntity toEntity(BooleanAttemptAnswer model);

    MultipleChoiceAttemptAnswer toModel(MultipleChoiceAttemptAnswerEntity entity);
    MultipleChoiceAttemptAnswerEntity toEntity(MultipleChoiceAttemptAnswer model);

    MatchingAttemptAnswer toModel(MatchingAttemptAnswerEntity entity);
    MatchingAttemptAnswerEntity toEntity(MatchingAttemptAnswer model);

    OpenAttemptAnswer toModel(OpenAttemptAnswerEntity entity);
    OpenAttemptAnswerEntity toEntity(OpenAttemptAnswer model);
}
