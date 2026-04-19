package net.developz.classroom.backend.catalog.exam.application.dto;

public record CreateExamRequest(
    String title,
    String description,
    String subjectId
) {}
