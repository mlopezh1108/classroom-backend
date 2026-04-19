package net.developz.classroom.backend.catalog.exam.application.dto;

public record CreateMatchingPairRequest(
    String leftSide,
    String rightSide
) {}
