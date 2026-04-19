package net.developz.classroom.backend.catalog.resource.application.usecase;

import net.developz.classroom.backend.catalog.resource.application.dto.UpdateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.Resource;
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
class UpdateResourceUseCaseTest {

    @Mock
    private ResourceRepositoryPort resourceRepositoryPort;

    @InjectMocks
    private UpdateResourceUseCase useCase;

    @Test
    void shouldUpdateResource() {
        String resourceId = "res-1";
        UpdateResourceRequest request = new UpdateResourceRequest("New Title", "new-url");
        Resource resource = new Resource();
        resource.setTitle("Old Title");
        
        when(resourceRepositoryPort.findById(resourceId)).thenReturn(Optional.of(resource));
        when(resourceRepositoryPort.save(resource)).thenReturn(resource);

        Resource result = useCase.execute(resourceId, request);

        assertThat(result.getTitle()).isEqualTo("New Title");
        assertThat(result.getContentUrl()).isEqualTo("new-url");
        verify(resourceRepositoryPort).save(resource);
    }
}
