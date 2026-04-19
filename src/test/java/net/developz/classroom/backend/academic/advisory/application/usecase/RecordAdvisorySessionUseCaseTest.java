package net.developz.classroom.backend.academic.advisory.application.usecase;

import net.developz.classroom.backend.academic.advisory.application.dto.RecordSessionRequest;
import net.developz.classroom.backend.academic.advisory.application.port.AdvisoryRepositoryPort;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.Advisory;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.enums.AdvisoryStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecordAdvisorySessionUseCaseTest {

    @Mock
    private AdvisoryRepositoryPort repositoryPort;

    @InjectMocks
    private RecordAdvisorySessionUseCase useCase;

    @Test
    void shouldRecordSession() {
        String advisoryId = "adv-1";
        Advisory advisory = new Advisory();
        RecordSessionRequest request = new RecordSessionRequest("Some notes", AdvisoryStatus.COMPLETED);
        
        when(repositoryPort.findById(advisoryId)).thenReturn(Optional.of(advisory));
        when(repositoryPort.save(advisory)).thenReturn(advisory);

        Advisory result = useCase.execute(advisoryId, request);

        assertThat(result.getNotes()).isEqualTo("Some notes");
        assertThat(result.getStatus()).isEqualTo(AdvisoryStatus.COMPLETED);
        verify(repositoryPort).save(advisory);
    }
}
