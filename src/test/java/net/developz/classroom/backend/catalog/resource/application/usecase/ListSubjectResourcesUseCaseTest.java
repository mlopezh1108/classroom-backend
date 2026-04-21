package net.developz.classroom.backend.catalog.resource.application.usecase;

import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListSubjectResourcesUseCaseTest {

    @Mock
    private ResourceRepositoryPort resourceRepositoryPort;

    @InjectMocks
    private ListSubjectResourcesUseCase useCase;

    @Test
    void shouldListResources() {
        String subjectId = "sub-1";
        List<Resource> resources = List.of(new Resource(), new Resource());
        when(resourceRepositoryPort.findBySubjectId(subjectId)).thenReturn(resources);

        List<Resource> result = useCase.execute(subjectId);

        assertThat(result).hasSize(2).isEqualTo(resources);
    }
}
