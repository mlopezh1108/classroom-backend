package net.developz.classroom.backend.academic.advisory.application.usecase;

import net.developz.classroom.backend.academic.advisory.application.port.AdvisoryRepositoryPort;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.Advisory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListAdvisoriesUseCaseTest {

    @Mock
    private AdvisoryRepositoryPort repositoryPort;

    @InjectMocks
    private ListAdvisoriesUseCase useCase;

    @Test
    void shouldListAdvisories() {
        String enrollmentId = "enroll-1";
        List<Advisory> advisories = List.of(new Advisory(), new Advisory());
        when(repositoryPort.findByEnrollmentId(enrollmentId)).thenReturn(advisories);

        List<Advisory> result = useCase.execute(enrollmentId);

        assertThat(result).hasSize(2).isEqualTo(advisories);
    }
}
