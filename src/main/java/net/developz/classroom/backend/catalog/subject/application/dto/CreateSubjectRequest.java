package net.developz.classroom.backend.catalog.subject.application.dto;

import java.util.Map;

public record CreateSubjectRequest(
    String subjectCode,
    Map<String, String> subjectNames,
    Integer level
) {}
