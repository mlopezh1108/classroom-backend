package net.developz.classroom.backend.catalog.application.dto;

import java.util.Map;

public record UpdateSubjectRequest(
        String subjectCode,
        Integer level,
        Map<String, String> subjectNames
) {
}
