package net.developz.classroom.backend.academic.enrollment.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.developz.classroom.backend.academic.enrollment.application.dto.EnrollmentDTO;
import net.developz.classroom.backend.academic.enrollment.application.dto.EnrollStudentRequest;
import net.developz.classroom.backend.academic.enrollment.application.usecase.DropCourseUseCase;
import net.developz.classroom.backend.academic.enrollment.application.usecase.EnrollStudentUseCase;
import net.developz.classroom.backend.academic.enrollment.application.usecase.GetCourseRosterUseCase;
import net.developz.classroom.backend.academic.enrollment.application.usecase.GenerateEnrollmentReportUseCase;
import net.developz.classroom.backend.academic.enrollment.infrastructure.mapper.EnrollmentMapper;
import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import net.developz.classroom.backend.iam.auth.infrastructure.security.jwt.JwtService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import java.util.List;

import static java.util.Objects.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EnrollmentController.class)
@AutoConfigureMockMvc(addFilters = false) // Bypass security filters for unit testing
class EnrollmentControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockitoBean
        private EnrollStudentUseCase enrollStudentUseCase;

        @MockitoBean
        private DropCourseUseCase dropCourseUseCase;

        @MockitoBean
        private GetCourseRosterUseCase getCourseRosterUseCase;

        @MockitoBean
        private GenerateEnrollmentReportUseCase generateEnrollmentReportUseCase;

        @MockitoBean
        private EnrollmentMapper mapper;

        @MockitoBean
        private JwtService jwtService;

        @MockitoBean
        private UserDetailsService userDetailsService;

        @Test
        void shouldEnrollStudent() throws Exception {
                EnrollStudentRequest request = new EnrollStudentRequest("student-123", "course-456");
                Enrollment enrollment = new Enrollment();
                EnrollmentDTO dto = new EnrollmentDTO("enroll-1", "John Doe", "CS101", "Computer Science", null);

                when(mapper.toModel(any(EnrollStudentRequest.class))).thenReturn(enrollment);
                when(enrollStudentUseCase.execute(any(Enrollment.class))).thenReturn(enrollment);
                when(mapper.toDto(any(Enrollment.class))).thenReturn(dto);

                mockMvc.perform(post("/api/v1/enrollments")
                                .contentType(requireNonNull(MediaType.APPLICATION_JSON))
                                .content(requireNonNull(objectMapper.writeValueAsString(request))))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value("enroll-1"));
        }

        @Test
        void shouldDropCourse() throws Exception {
                mockMvc.perform(delete("/api/v1/enrollments/enroll-123"))
                                .andExpect(status().isNoContent());

                verify(dropCourseUseCase).execute("enroll-123");
        }

        @Test
        void shouldGetCourseRoster() throws Exception {
                PaginatedResult<Enrollment> result = new PaginatedResult<>(
                                List.of(new Enrollment()), 1L, 1, 0, 10);
                when(getCourseRosterUseCase.execute(eq("course-1"), any(PaginationCriteria.class)))
                                .thenReturn(result);

                mockMvc.perform(get("/api/v1/enrollments/roster/course-1"))
                                .andExpect(status().isOk());
        }
}
