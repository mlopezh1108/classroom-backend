package net.developz.classroom.backend.academic.assessment.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.developz.classroom.backend.academic.assessment.application.dto.ExamAttemptDTO;
import net.developz.classroom.backend.academic.assessment.application.dto.StartExamAttemptRequest;
import net.developz.classroom.backend.academic.assessment.application.dto.SubmitAnswerRequest;
import net.developz.classroom.backend.academic.assessment.application.dto.AnswerType;
import net.developz.classroom.backend.academic.assessment.application.usecase.*;
import net.developz.classroom.backend.academic.assessment.infrastructure.mapper.AssessmentMapper;
import net.developz.classroom.backend.academic.assessment.domain.model.ExamAttempt;
import net.developz.classroom.backend.academic.assessment.domain.model.enums.AttemptStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import net.developz.classroom.backend.iam.auth.infrastructure.security.jwt.JwtService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssessmentController.class)
@AutoConfigureMockMvc(addFilters = false)
class AssessmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private StartExamAttemptUseCase startExamAttemptUseCase;

    @MockitoBean
    private SubmitExamAnswerUseCase submitExamAnswerUseCase;

    @MockitoBean
    private FinishExamAttemptUseCase finishExamAttemptUseCase;

    @MockitoBean
    private AutoGradeAttemptUseCase autoGradeAttemptUseCase;

    @MockitoBean
    private ReviewAssessmentUseCase reviewAssessmentUseCase;

    @MockitoBean
    private AssessmentMapper mapper;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @Test
    void shouldStartAttempt() throws Exception {
        StartExamAttemptRequest request = new StartExamAttemptRequest("enroll-1", "exam-1");
        ExamAttempt attempt = new ExamAttempt();
        ExamAttemptDTO dto = new ExamAttemptDTO("att-1", "enroll-1", "exam-1", null, null, null, AttemptStatus.STARTED);

        when(mapper.toModel(any(StartExamAttemptRequest.class))).thenReturn(attempt);
        when(startExamAttemptUseCase.execute(any(ExamAttempt.class))).thenReturn(attempt);
        when(mapper.toDto(any(ExamAttempt.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/assessments/attempts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("att-1"));
    }

    @Test
    void shouldSubmitAnswer() throws Exception {
        SubmitAnswerRequest request = new SubmitAnswerRequest("q-1", AnswerType.BOOLEAN, "true");
        ExamAttempt attempt = new ExamAttempt();
        ExamAttemptDTO dto = new ExamAttemptDTO("att-1", "enroll-1", "exam-1", null, null, null, AttemptStatus.IN_PROGRESS);

        when(submitExamAnswerUseCase.execute(eq("att-1"), any(SubmitAnswerRequest.class))).thenReturn(attempt);
        when(mapper.toDto(any(ExamAttempt.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/assessments/attempts/att-1/answers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("att-1"));
    }
}
