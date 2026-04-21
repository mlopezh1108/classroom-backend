package net.developz.classroom.backend.catalog.exam.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.exam.application.dto.CreateExamRequest;
import net.developz.classroom.backend.catalog.exam.application.dto.CreateQuestionRequest;
import net.developz.classroom.backend.catalog.exam.application.dto.ExamDTO;
import net.developz.classroom.backend.catalog.exam.application.usecase.AddQuestionUseCase;
import net.developz.classroom.backend.catalog.exam.application.usecase.CreateExamUseCase;
import net.developz.classroom.backend.catalog.exam.application.usecase.DeleteQuestionUseCase;
import net.developz.classroom.backend.catalog.exam.application.usecase.GetExamDetailsUseCase;
import net.developz.classroom.backend.catalog.exam.infrastructure.mapper.ExamMapper;
import net.developz.classroom.backend.catalog.exam.domain.model.Exam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exams")
@RequiredArgsConstructor
public class ExamController {

    private final CreateExamUseCase createExamUseCase;
    private final AddQuestionUseCase addQuestionUseCase;
    private final DeleteQuestionUseCase deleteQuestionUseCase;
    private final GetExamDetailsUseCase getExamDetailsUseCase;
    private final ExamMapper mapper;

    @PostMapping
    public ResponseEntity<ExamDTO> createExam(@RequestBody CreateExamRequest request) {
        Exam saved = createExamUseCase.execute(request);
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
    }

    /**
     * Adds a polymorphic Question child directly to the current Exam aggregate.
     * 
     * IMPORTANT USAGE EXPLANATION:
     * Due to the varying physical structure of different underlying question styles
     * (Multiple Choice, Open, Boolean, Matching), the CreateQuestionRequest acts
     * as a Flexible Wrapper object mapped through a single shared endpoint.
     * 
     * Provide exact inputs depending on the `questionType` specified:
     * - `BOOLEAN`: Define the `correctAnswer` boolean.
     * - `MULTIPLE_CHOICE`: Provide the `options` array.
     * - `MATCHING`: Provide the `matchingPairs` array.
     * - `OPEN`: Do not instantiate any subclasses, only text/points/orderIndex.
     */
    @PostMapping("/questions")
    public ResponseEntity<ExamDTO> addQuestion(@RequestBody CreateQuestionRequest request) {
        Exam saved = addQuestionUseCase.execute(request);
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String questionId) {
        deleteQuestionUseCase.execute(questionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{examId}")
    public ResponseEntity<ExamDTO> getExamDetails(@PathVariable String examId) {
        Exam exam = getExamDetailsUseCase.execute(examId);
        return ResponseEntity.ok(mapper.toDto(exam));
    }
}
