package sw2.project.feature.qa;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.project.domain.question.Question;
import sw2.project.feature.qa.dto.AnswerCreateRequest;
import sw2.project.feature.qa.dto.AnswerResponse;
import sw2.project.feature.qa.dto.QuestionCreateRequest;
import sw2.project.feature.qa.dto.QuestionResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(@Valid @RequestBody QuestionCreateRequest request) {
        Question question = questionService.createQuestion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new QuestionResponse(question));
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> getAllQuestions() {
        List<Question> questions = questionService.findAllQuestions();
        List<QuestionResponse> response = questions.stream()
                .map(QuestionResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable Long questionId) {
        Question question = questionService.findQuestionById(questionId);
        return ResponseEntity.ok(new QuestionResponse(question));
    }

    @PostMapping("/{questionId}/answers")
    public ResponseEntity<AnswerResponse> createAnswer(
            @PathVariable Long questionId,
            @Valid @RequestBody AnswerCreateRequest request) {
        sw2.project.domain.question.Answer answer = questionService.createAnswer(questionId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AnswerResponse(answer));
    }
}
