package net.developz.classroom.backend.academic.advisory.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.developz.classroom.backend.academic.advisory.application.dto.AdvisoryDTO;
import net.developz.classroom.backend.academic.advisory.application.dto.RecordSessionRequest;
import net.developz.classroom.backend.academic.advisory.application.dto.ScheduleAdvisoryRequest;
import net.developz.classroom.backend.academic.advisory.application.usecase.ListAdvisoriesUseCase;
import net.developz.classroom.backend.academic.advisory.application.usecase.RecordAdvisorySessionUseCase;
import net.developz.classroom.backend.academic.advisory.application.usecase.ScheduleAdvisoryUseCase;
import net.developz.classroom.backend.academic.advisory.infrastructure.mapper.AdvisoryMapper;
import net.developz.classroom.backend.academic.advisory.domain.model.Advisory;
import net.developz.classroom.backend.academic.advisory.domain.model.enums.AdvisoryStatus;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import net.developz.classroom.backend.iam.auth.infrastructure.security.jwt.JwtService;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdvisoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdvisoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ScheduleAdvisoryUseCase scheduleAdvisoryUseCase;

    @MockitoBean
    private RecordAdvisorySessionUseCase recordAdvisorySessionUseCase;

    @MockitoBean
    private ListAdvisoriesUseCase listAdvisoriesUseCase;

    @MockitoBean
    private AdvisoryMapper mapper;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @Test
    void shouldScheduleAdvisory() throws Exception {
        ScheduleAdvisoryRequest request = new ScheduleAdvisoryRequest("enroll-1", LocalDate.now(), LocalTime.now());
        Advisory advisory = new Advisory();
        AdvisoryDTO dto = new AdvisoryDTO("adv-1", "enroll-1", AdvisoryStatus.SCHEDULED, LocalDate.now(),
                LocalTime.now(), null);

        when(mapper.toModel(any(ScheduleAdvisoryRequest.class))).thenReturn(advisory);
        when(scheduleAdvisoryUseCase.execute(any(Advisory.class))).thenReturn(advisory);
        when(mapper.toDTO(any(Advisory.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/advisories/schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("adv-1"));
    }

    @Test
    void shouldRecordSession() throws Exception {
        RecordSessionRequest request = new RecordSessionRequest("Notes", AdvisoryStatus.COMPLETED);
        Advisory advisory = new Advisory();
        AdvisoryDTO dto = new AdvisoryDTO("adv-1", "enroll-1", AdvisoryStatus.COMPLETED, LocalDate.now(),
                LocalTime.now(), "Notes");

        when(recordAdvisorySessionUseCase.execute(eq("adv-1"), any(RecordSessionRequest.class))).thenReturn(advisory);
        when(mapper.toDTO(any(Advisory.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/advisories/adv-1/record")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.notes").value("Notes"));
    }
}
