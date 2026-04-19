package net.developz.classroom.backend.catalog.exam.application.dto;

public record CreateQuestionOptionRequest(
    String text,
    Boolean isCorrect
) {}
