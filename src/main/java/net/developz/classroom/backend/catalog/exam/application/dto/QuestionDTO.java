package net.developz.classroom.backend.catalog.exam.application.dto;

import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.QuestionType;

import java.util.List;

public record QuestionDTO(
    String id,
    String text,
    Integer orderIndex,
    Double points,
    QuestionType questionType,
    
    // Sub-properties flattened for DTO
    Boolean correctAnswer,
    List<QuestionOptionDTO> options,
    List<MatchingPairDTO> matchingPairs
) {}
