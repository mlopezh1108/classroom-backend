package net.developz.classroom.backend.catalog.resource.application.usecase;

import net.developz.classroom.backend.catalog.resource.application.dto.CreateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.infrastructure.mapper.ResourceMapper;
import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.constant.ResourceType;
import net.developz.classroom.backend.catalog.subject.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateResourceUseCaseTest {

    @Mock
    private ResourceRepositoryPort resourceRepositoryPort;

    @Mock
    private SubjectRepositoryPort subjectRepositoryPort;

    @Mock
    private ResourceMapper resourceMapper;

    @InjectMocks
    private CreateResourceUseCase useCase;

    @Test
    void shouldCreateResource() {
        String subjectId = "sub-1";
        CreateResourceRequest request = new CreateResourceRequest("Title", ResourceType.PDF, "url", subjectId);
        Resource resource = new Resource();

        when(subjectRepositoryPort.existsById(subjectId)).thenReturn(true);
        when(resourceMapper.toModel(request)).thenReturn(resource);
        when(resourceRepositoryPort.save(any(Resource.class))).thenReturn(resource);

        Resource result = useCase.execute(request);

        assertThat(result).isNotNull();
        verify(resourceRepositoryPort).save(any(Resource.class));
    }

    @Test
    void shouldThrowExceptionWhenSubjectNotFound() {
        String subjectId = "unknown";
        CreateResourceRequest request = new CreateResourceRequest("Title", ResourceType.PDF, "url", subjectId);

        when(subjectRepositoryPort.existsById(subjectId)).thenReturn(false);

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
