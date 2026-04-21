package net.developz.classroom.backend.academic.advisory.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.advisory.application.dto.RecordSessionRequest;
import net.developz.classroom.backend.academic.advisory.application.port.AdvisoryRepositoryPort;
import net.developz.classroom.backend.academic.advisory.domain.model.Advisory;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class RecordAdvisorySessionUseCase {
    private final AdvisoryRepositoryPort advisoryRepositoryPort;

    public Advisory execute(String advisoryId, RecordSessionRequest request) {
        Advisory advisory = advisoryRepositoryPort.findById(advisoryId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Advisory not found with id: " + advisoryId, this.getClass(), Advisory.class));

        advisory.setNotes(request.notes());
        advisory.setStatus(request.status());
        
        return advisoryRepositoryPort.save(advisory);
    }
}
