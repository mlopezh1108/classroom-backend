package net.developz.classroom.backend.catalog.exam.application.dto;

import java.util.List;

public record ExamDTO(
    String id,
    String title,
    String description,
    String subjectId,
    List<QuestionDTO> questions
) {}
