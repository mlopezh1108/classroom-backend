package net.developz.classroom.backend.catalog.exam.application.dto;

import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.QuestionType;

import java.util.List;

public record CreateQuestionRequest(
    String examId,
    String text,
    Integer orderIndex,
    Double points,
    QuestionType questionType,
    
    // For BOOLEAN Question
    Boolean correctAnswer,
    
    // For MULTIPLE_CHOICE Question
    List<CreateQuestionOptionRequest> options,
    
    // For MATCHING Question
    List<CreateMatchingPairRequest> matchingPairs
) {}
