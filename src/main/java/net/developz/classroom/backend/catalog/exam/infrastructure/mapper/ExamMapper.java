package net.developz.classroom.backend.catalog.exam.infrastructure.mapper;

import net.developz.classroom.backend.catalog.exam.application.dto.*;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExamMapper {
    
    ExamDTO toDTO(Exam exam);

    default QuestionDTO toDTO(Question question) {
        if (question == null) return null;
        
        Boolean correctAnswer = null;
        java.util.List<QuestionOptionDTO> options = null;
        java.util.List<MatchingPairDTO> pairs = null;
        
        if (question instanceof BooleanQuestion bq) {
            correctAnswer = bq.getCorrectAnswer();
        } else if (question instanceof MultipleChoiceQuestion mc) {
            options = mapOptions(mc.getOptions());
        } else if (question instanceof MatchingQuestion mq) {
            pairs = mapPairs(mq.getPairs());
        }
        
        return new QuestionDTO(
                question.getId(),
                question.getText(),
                question.getOrderIndex(),
                question.getPoints(),
                question.getQuestionType(),
                correctAnswer,
                options,
                pairs
        );
    }
    
    java.util.List<QuestionOptionDTO> mapOptions(java.util.List<QuestionOption> options);
    
    java.util.List<MatchingPairDTO> mapPairs(java.util.List<MatchingPair> pairs);
}
