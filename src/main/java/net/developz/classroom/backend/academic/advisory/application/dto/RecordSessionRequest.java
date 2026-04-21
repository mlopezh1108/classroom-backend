package net.developz.classroom.backend.academic.advisory.application.dto;

import net.developz.classroom.backend.academic.advisory.domain.model.enums.AdvisoryStatus;

public record RecordSessionRequest(
    String notes,
    AdvisoryStatus status
) {}
