package sw2.project.feature.answer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.project.domain.question.Answer;
import sw2.project.feature.qa.QuestionService;
import sw2.project.feature.qa.dto.AnswerCreateRequest;
import sw2.project.feature.qa.dto.AnswerResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/questions/{questionId}/answers")
@RequiredArgsConstructor
public class AnswerController implements AnswerControllerDocs {

    private final QuestionService questionService;

    @Override
    @GetMapping
    public ResponseEntity<List<AnswerResponse>> getAnswersForQuestion(@PathVariable Long questionId) {
        List<Answer> answers = questionService.findAnswersByQuestionId(questionId);
        List<AnswerResponse> response = answers.stream()
                .map(AnswerResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<AnswerResponse> createAnswer(
            @PathVariable Long questionId,
            @Valid @RequestBody AnswerCreateRequest request) {
        Answer newAnswer = questionService.createAnswer(questionId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AnswerResponse(newAnswer));
    }
}
