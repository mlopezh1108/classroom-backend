package net.developz.classroom.backend.academic.advisory.application.usecase;

import net.developz.classroom.backend.academic.advisory.application.port.AdvisoryRepositoryPort;
import net.developz.classroom.backend.academic.advisory.domain.model.Advisory;
import net.developz.classroom.backend.academic.advisory.domain.model.enums.AdvisoryStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScheduleAdvisoryUseCaseTest {

    @Mock
    private AdvisoryRepositoryPort repositoryPort;

    @InjectMocks
    private ScheduleAdvisoryUseCase useCase;

    @Test
    void shouldScheduleAdvisory() {
        Advisory advisory = new Advisory();
        when(repositoryPort.save(advisory)).thenReturn(advisory);

        Advisory result = useCase.execute(advisory);

        assertThat(result.getStatus()).isEqualTo(AdvisoryStatus.SCHEDULED);
        verify(repositoryPort).save(advisory);
    }
}
