package net.developz.classroom.backend.catalog.exam.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.exam.application.dto.CreateMatchingPairRequest;
import net.developz.classroom.backend.catalog.exam.application.dto.CreateQuestionOptionRequest;
import net.developz.classroom.backend.catalog.exam.application.dto.CreateQuestionRequest;
import net.developz.classroom.backend.catalog.exam.application.port.ExamRepositoryPort;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.*;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class AddQuestionUseCase {
    private final ExamRepositoryPort examRepositoryPort;

    public Exam execute(CreateQuestionRequest request) {
        Exam exam = examRepositoryPort.findById(request.examId())
                .orElseThrow(() -> new EntityNotFoundException("Exam not found", AddQuestionUseCase.class, Exam.class));

        Question question;
        switch (request.questionType()) {
            case BOOLEAN -> {
                BooleanQuestion boolQ = new BooleanQuestion();
                boolQ.setCorrectAnswer(request.correctAnswer());
                question = boolQ;
            }
            case MULTIPLE_CHOICE -> {
                MultipleChoiceQuestion mcQ = new MultipleChoiceQuestion();
                if (request.options() != null) {
                    for (CreateQuestionOptionRequest optReq : request.options()) {
                        QuestionOption option = new QuestionOption();
                        option.setText(optReq.text());
                        option.setIsCorrect(optReq.isCorrect());
                        option.setQuestion(mcQ);
                        mcQ.getOptions().add(option);
                    }
                }
                question = mcQ;
            }
            case MATCHING -> {
                MatchingQuestion matchQ = new MatchingQuestion();
                if (request.matchingPairs() != null) {
                    for (CreateMatchingPairRequest matchReq : request.matchingPairs()) {
                        MatchingPair pair = new MatchingPair();
                        pair.setLeftSide(matchReq.leftSide());
                        pair.setRightSide(matchReq.rightSide());
                        pair.setQuestion(matchQ);
                        matchQ.getPairs().add(pair);
                    }
                }
                question = matchQ;
            }
            case OPEN -> {
                question = new OpenQuestion();
            }
            default -> throw new IllegalArgumentException("Unsupported question type");
        }

        question.setText(request.text());
        question.setOrderIndex(request.orderIndex());
        question.setPoints(request.points());
        question.setExam(exam);

        exam.getQuestions().add(question);
        return examRepositoryPort.save(exam);
    }
}
