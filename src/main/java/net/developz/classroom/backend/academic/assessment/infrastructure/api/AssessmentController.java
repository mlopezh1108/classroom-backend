package net.developz.classroom.backend.academic.assessment.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.assessment.application.dto.ExamAttemptDTO;
import net.developz.classroom.backend.academic.assessment.application.dto.GradeAssessmentRequest;
import net.developz.classroom.backend.academic.assessment.application.dto.StartExamAttemptRequest;
import net.developz.classroom.backend.academic.assessment.application.dto.SubmitAnswerRequest;
import net.developz.classroom.backend.academic.assessment.application.usecase.*;
import net.developz.classroom.backend.academic.assessment.infrastructure.mapper.AssessmentMapper;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttempt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assessments")
@RequiredArgsConstructor
public class AssessmentController {

    private final StartExamAttemptUseCase startExamAttemptUseCase;
    private final SubmitExamAnswerUseCase submitExamAnswerUseCase;
    private final FinishExamAttemptUseCase finishExamAttemptUseCase;
    private final AutoGradeAttemptUseCase autoGradeAttemptUseCase;
    private final ReviewAssessmentUseCase reviewAssessmentUseCase;
    private final AssessmentMapper mapper;

    @PostMapping("/attempts")
    public ResponseEntity<ExamAttemptDTO> startAttempt(@RequestBody StartExamAttemptRequest request) {
        ExamAttempt entity = mapper.toEntity(request);
        ExamAttempt saved = startExamAttemptUseCase.execute(entity);
        return new ResponseEntity<>(mapper.toDTO(saved), HttpStatus.CREATED);
    }

    @PostMapping("/attempts/{attemptId}/answers")
    public ResponseEntity<ExamAttemptDTO> submitAnswer(
            @PathVariable String attemptId,
            @RequestBody SubmitAnswerRequest request) {
        ExamAttempt attempt = submitExamAnswerUseCase.execute(attemptId, request);
        return ResponseEntity.ok(mapper.toDTO(attempt));
    }

    @PostMapping("/attempts/{attemptId}/finish")
    public ResponseEntity<ExamAttemptDTO> finishAttempt(@PathVariable String attemptId) {
        ExamAttempt attempt = finishExamAttemptUseCase.execute(attemptId);
        return ResponseEntity.ok(mapper.toDTO(attempt));
    }

    @PostMapping("/attempts/{attemptId}/auto-grade")
    public ResponseEntity<ExamAttemptDTO> autoGradeAttempt(@PathVariable String attemptId) {
        ExamAttempt attempt = autoGradeAttemptUseCase.execute(attemptId);
        return ResponseEntity.ok(mapper.toDTO(attempt));
    }

    @PostMapping("/attempts/{attemptId}/review")
    public ResponseEntity<ExamAttemptDTO> reviewAssessment(
            @PathVariable String attemptId,
            @RequestBody List<GradeAssessmentRequest> requests) {
        ExamAttempt attempt = reviewAssessmentUseCase.execute(attemptId, requests);
        return ResponseEntity.ok(mapper.toDTO(attempt));
    }
}
