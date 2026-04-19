package net.developz.classroom.backend.catalog.exam.application.dto;

public record QuestionOptionDTO(
    String id,
    String text,
    Boolean isCorrect
) {}
