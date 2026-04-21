package net.developz.classroom.backend.catalog.exam.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.developz.classroom.backend.catalog.exam.application.dto.CreateExamRequest;
import net.developz.classroom.backend.catalog.exam.application.dto.CreateQuestionRequest;
import net.developz.classroom.backend.catalog.exam.application.dto.ExamDTO;
import net.developz.classroom.backend.catalog.exam.application.usecase.AddQuestionUseCase;
import net.developz.classroom.backend.catalog.exam.application.usecase.CreateExamUseCase;
import net.developz.classroom.backend.catalog.exam.application.usecase.DeleteQuestionUseCase;
import net.developz.classroom.backend.catalog.exam.application.usecase.GetExamDetailsUseCase;
import net.developz.classroom.backend.catalog.exam.infrastructure.mapper.ExamMapper;
import net.developz.classroom.backend.catalog.exam.domain.model.Exam;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.QuestionType;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExamController.class)
@AutoConfigureMockMvc(addFilters = false)
class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateExamUseCase createExamUseCase;

    @MockitoBean
    private AddQuestionUseCase addQuestionUseCase;

    @MockitoBean
    private DeleteQuestionUseCase deleteQuestionUseCase;

    @MockitoBean
    private GetExamDetailsUseCase getExamDetailsUseCase;

    @MockitoBean
    private ExamMapper mapper;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @Test
    void shouldCreateExam() throws Exception {
        CreateExamRequest request = new CreateExamRequest("Exam Title", "Desc", "sub-1");
        Exam exam = new Exam();
        ExamDTO dto = new ExamDTO("exam-1", "Exam Title", "Desc", "sub-1", null);

        when(createExamUseCase.execute(any(CreateExamRequest.class))).thenReturn(exam);
        when(mapper.toDto(any(Exam.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/exams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("exam-1"));
    }

    @Test
    void shouldAddQuestion() throws Exception {
        CreateQuestionRequest request = new CreateQuestionRequest("exam-1", "Text", 1, 5.0, QuestionType.BOOLEAN, true, null, null);
        Exam exam = new Exam();
        ExamDTO dto = new ExamDTO("exam-1", "Exam Title", "Desc", "sub-1", null);

        when(addQuestionUseCase.execute(any(CreateQuestionRequest.class))).thenReturn(exam);
        when(mapper.toDto(any(Exam.class))).thenReturn(dto);

        mockMvc.perform(post("/api/v1/exams/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteQuestion() throws Exception {
        mockMvc.perform(delete("/api/v1/exams/questions/q-1"))
                .andExpect(status().isNoContent());

        verify(deleteQuestionUseCase).execute("q-1");
    }
}
