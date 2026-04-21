package net.developz.classroom.backend.catalog.exam.infrastructure.mapper;

import net.developz.classroom.backend.catalog.exam.application.dto.*;
import net.developz.classroom.backend.catalog.exam.domain.model.*;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExamMapper {

    // --- Entity <-> Domain (Exam) ---
    Exam toModel(ExamEntity entity);
    ExamEntity toEntity(Exam model);

    // --- Entity <-> Domain (Questions) ---
    default Question toModel(QuestionEntity entity) {
        if (entity == null) return null;
        if (entity instanceof BooleanQuestionEntity bqe) return toModel(bqe);
        if (entity instanceof MultipleChoiceQuestionEntity mcqe) return toModel(mcqe);
        if (entity instanceof MatchingQuestionEntity mqe) return toModel(mqe);
        if (entity instanceof OpenQuestionEntity oqe) return toModel(oqe);
        return null;
    }

    BooleanQuestion toModel(BooleanQuestionEntity entity);
    MultipleChoiceQuestion toModel(MultipleChoiceQuestionEntity entity);
    MatchingQuestion toModel(MatchingQuestionEntity entity);
    OpenQuestion toModel(OpenQuestionEntity entity);

    default QuestionEntity toEntity(Question model) {
        if (model == null) return null;
        if (model instanceof BooleanQuestion bq) return toEntity(bq);
        if (model instanceof MultipleChoiceQuestion mcq) return toEntity(mcq);
        if (model instanceof MatchingQuestion mq) return toEntity(mq);
        if (model instanceof OpenQuestion oq) return toEntity(oq);
        return null;
    }

    BooleanQuestionEntity toEntity(BooleanQuestion model);
    MultipleChoiceQuestionEntity toEntity(MultipleChoiceQuestion model);
    MatchingQuestionEntity toEntity(MatchingQuestion model);
    OpenQuestionEntity toEntity(OpenQuestion model);

    // Support for auxiliaries
    QuestionOption toModel(QuestionOptionEntity entity);
    QuestionOptionEntity toEntity(QuestionOption model);
    MatchingPair toModel(MatchingPairEntity entity);
    MatchingPairEntity toEntity(MatchingPair model);

    // --- Domain -> DTO ---
    @Mapping(target = "questions", expression = "java(mapQuestionsToDto(model.getQuestions()))")
    ExamDTO toDto(Exam model);

    default List<QuestionDTO> mapQuestionsToDto(List<Question> questions) {
        if (questions == null) return null;
        return questions.stream().map(this::toDto).collect(Collectors.toList());
    }

    default QuestionDTO toDto(Question question) {
        if (question == null) return null;

        Boolean correctAnswer = null;
        List<QuestionOptionDTO> options = null;
        List<MatchingPairDTO> matchingPairs = null;

        if (question instanceof BooleanQuestion bq) {
            correctAnswer = bq.getCorrectAnswer();
        } else if (question instanceof MultipleChoiceQuestion mc) {
            options = mapOptionsToDto(mc.getOptions());
        } else if (question instanceof MatchingQuestion mq) {
            matchingPairs = mapPairsToDto(mq.getPairs());
        }

        return new QuestionDTO(
                question.getId(),
                question.getText(),
                question.getOrderIndex(),
                question.getPoints(),
                question.getQuestionType(),
                correctAnswer,
                options,
                matchingPairs
        );
    }

    List<QuestionOptionDTO> mapOptionsToDto(List<QuestionOption> options);
    List<MatchingPairDTO> mapPairsToDto(List<MatchingPair> pairs);
}
