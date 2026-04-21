package net.developz.classroom.backend.catalog.resource.application.usecase;

import net.developz.classroom.backend.catalog.resource.application.dto.UpdateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.infrastructure.mapper.ResourceMapper;
import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateResourceUseCaseTest {

    @Mock
    private ResourceRepositoryPort resourceRepositoryPort;

    @Mock
    private ResourceMapper resourceMapper;

    @InjectMocks
    private UpdateResourceUseCase useCase;

    @Test
    void shouldUpdateResource() {
        String resourceId = "res-1";
        UpdateResourceRequest request = new UpdateResourceRequest("New Title", "new-url");
        Resource resource = new Resource();
        resource.setTitle("Old Title");
        
        when(resourceRepositoryPort.findById(resourceId)).thenReturn(Optional.of(resource));
        doAnswer(invocation -> {
            UpdateResourceRequest req = invocation.getArgument(0);
            Resource res = invocation.getArgument(1);
            res.setTitle(req.title());
            res.setContentUrl(req.contentUrl());
            return null;
        }).when(resourceMapper).updateModelFromRequest(any(), any());
        
        when(resourceRepositoryPort.save(resource)).thenReturn(resource);

        Resource result = useCase.execute(resourceId, request);

        assertThat(result.getTitle()).isEqualTo("New Title");
        assertThat(result.getContentUrl()).isEqualTo("new-url");
        verify(resourceRepositoryPort).save(resource);
    }
}
