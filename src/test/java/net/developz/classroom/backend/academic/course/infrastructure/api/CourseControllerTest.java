package net.developz.classroom.backend.academic.course.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.developz.classroom.backend.academic.course.application.dto.CourseResponse;
import net.developz.classroom.backend.academic.course.application.dto.CreateCourseRequest;
import net.developz.classroom.backend.academic.course.application.usecase.CreateCourseUseCase;
import net.developz.classroom.backend.academic.course.application.usecase.FindCourseByIdUseCase;
import net.developz.classroom.backend.academic.course.application.usecase.ListTeacherCoursesUseCase;
import net.developz.classroom.backend.academic.course.infrastructure.mapper.CourseMapper;
import net.developz.classroom.backend.academic.course.domain.model.Course;
import net.developz.classroom.backend.academic.course.domain.model.enums.CourseStatus;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
@AutoConfigureMockMvc(addFilters = false)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateCourseUseCase createCourseUseCase;

    @MockitoBean
    private FindCourseByIdUseCase findCourseByIdUseCase;

    @MockitoBean
    private ListTeacherCoursesUseCase listTeacherCoursesUseCase;

    @MockitoBean
    private CourseMapper courseMapper;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @Test
    void shouldCreateCourse() throws Exception {
        CreateCourseRequest request = new CreateCourseRequest("MATH101", "TEA-1", "GRP-1", "SUB-1", "PER-1",
                CourseStatus.OPEN);
        Course course = new Course();
        CourseResponse response = new CourseResponse("course-1", "MATH101", "TEA-1", "GRP-1", "SUB-1", "PER-1",
                CourseStatus.OPEN);

        when(courseMapper.toModel(any(CreateCourseRequest.class))).thenReturn(course);
        when(createCourseUseCase.execute(any(Course.class))).thenReturn(course);
        when(courseMapper.toResponse(any(Course.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/academic/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("course-1"))
                .andExpect(jsonPath("$.courseCode").value("MATH101"));
    }

    @Test
    void shouldGetCourseById() throws Exception {
        Course course = new Course();
        CourseResponse response = new CourseResponse("course-1", "MATH101", "TEA-1", "GRP-1", "SUB-1", "PER-1",
                CourseStatus.OPEN);

        when(findCourseByIdUseCase.execute("course-1")).thenReturn(course);
        when(courseMapper.toResponse(any(Course.class))).thenReturn(response);

        mockMvc.perform(get("/api/v1/academic/courses/course-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("course-1"));
    }

    @Test
    void shouldListTeacherCourses() throws Exception {
        CourseResponse response = new CourseResponse("course-1", "MATH101", "TEA-1", "GRP-1", "SUB-1", "PER-1",
                CourseStatus.OPEN);
        when(listTeacherCoursesUseCase.execute(eq("TEA-1"), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(new Course())));
        when(courseMapper.toResponse(any(Course.class))).thenReturn(response);

        mockMvc.perform(get("/api/v1/academic/courses/teacher/TEA-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].courseCode").value("MATH101"));
    }
}
