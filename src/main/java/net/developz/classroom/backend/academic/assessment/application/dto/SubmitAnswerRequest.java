package net.developz.classroom.backend.academic.assessment.application.dto;

public record SubmitAnswerRequest(
    String questionId,
    AnswerType answerType,
    String responseValue
) {}
