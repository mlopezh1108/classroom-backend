package net.developz.classroom.backend.catalog.resource.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.developz.classroom.backend.catalog.resource.application.dto.CreateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.dto.ResourceDTO;
import net.developz.classroom.backend.catalog.resource.application.dto.UpdateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.usecase.CreateResourceUseCase;
import net.developz.classroom.backend.catalog.resource.application.usecase.DeleteResourceUseCase;
import net.developz.classroom.backend.catalog.resource.application.usecase.ListSubjectResourcesUseCase;
import net.developz.classroom.backend.catalog.resource.application.usecase.UpdateResourceUseCase;
import net.developz.classroom.backend.catalog.resource.infrastructure.mapper.ResourceMapper;
import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.constant.ResourceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import net.developz.classroom.backend.iam.auth.infrastructure.security.jwt.JwtService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResourceController.class)
@AutoConfigureMockMvc(addFilters = false)
class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateResourceUseCase createResourceUseCase;

    @MockitoBean
    private UpdateResourceUseCase updateResourceUseCase;

    @MockitoBean
    private DeleteResourceUseCase deleteResourceUseCase;

    @MockitoBean
    private ListSubjectResourcesUseCase listSubjectResourcesUseCase;

    @MockitoBean
    private ResourceMapper mapper;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @Test
    void shouldCreateResource() throws Exception {
        CreateResourceRequest request = new CreateResourceRequest("Title", ResourceType.PDF, "url", "sub-1");
        Resource resource = new Resource();
        ResourceDTO dto = new ResourceDTO("res-1", "Title", ResourceType.PDF, "url", "sub-1");

        when(createResourceUseCase.execute(any(CreateResourceRequest.class))).thenReturn(resource);
        when(mapper.toDto(any(Resource.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/resources")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("res-1"));
    }

    @Test
    void shouldUpdateResource() throws Exception {
        UpdateResourceRequest request = new UpdateResourceRequest("New Title", "new-url");
        Resource resource = new Resource();
        ResourceDTO dto = new ResourceDTO("res-1", "New Title", ResourceType.PDF, "new-url", "sub-1");

        when(updateResourceUseCase.execute(eq("res-1"), any(UpdateResourceRequest.class))).thenReturn(resource);
        when(mapper.toDto(any(Resource.class))).thenReturn(dto);

        mockMvc.perform(put("/api/v1/resources/res-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Title"));
    }

    @Test
    void shouldListSubjectResources() throws Exception {
        when(listSubjectResourcesUseCase.execute(eq("sub-1"), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(new Resource())));

        mockMvc.perform(get("/api/v1/resources/subject/sub-1"))
                .andExpect(status().isOk());
    }
}
