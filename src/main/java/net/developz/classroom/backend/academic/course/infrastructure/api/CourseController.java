package net.developz.classroom.backend.academic.course.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.course.application.dto.CourseResponse;
import net.developz.classroom.backend.academic.course.application.dto.CreateCourseRequest;
import net.developz.classroom.backend.academic.course.application.usecase.CreateCourseUseCase;
import net.developz.classroom.backend.academic.course.application.usecase.FindCourseByIdUseCase;
import net.developz.classroom.backend.academic.course.application.usecase.ListTeacherCoursesUseCase;
import net.developz.classroom.backend.academic.course.infrastructure.mapper.CourseMapper;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/academic/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CreateCourseUseCase createCourseUseCase;
    private final FindCourseByIdUseCase findCourseByIdUseCase;
    private final ListTeacherCoursesUseCase listTeacherCoursesUseCase;
    private final CourseMapper courseMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('COURSE_CREATE')")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CreateCourseRequest request) {
        Course course = courseMapper.toEntity(request);
        Course savedCourse = createCourseUseCase.execute(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseMapper.toResponse(savedCourse));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('COURSE_VIEW')")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable String id) {
        Course course = findCourseByIdUseCase.execute(id);
        return ResponseEntity.ok(courseMapper.toResponse(course));
    }

    @GetMapping("/teacher/{teacherId}")
    @PreAuthorize("hasAuthority('COURSE_VIEW')")
    public ResponseEntity<List<CourseResponse>> getCoursesByTeacher(@PathVariable String teacherId) {
        List<CourseResponse> courses = listTeacherCoursesUseCase.execute(teacherId).stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courses);
    }
}
