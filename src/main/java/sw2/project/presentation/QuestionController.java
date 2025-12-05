package sw2.project.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.project.application.QuestionService;
import sw2.project.domain.Question;
import sw2.project.presentation.dto.QuestionCreateRequest;
import sw2.project.presentation.dto.QuestionResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController implements QuestionControllerDocs {

    private final QuestionService questionService;

    @Override
    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(@Valid @RequestBody QuestionCreateRequest request) {
        Question newQuestion = questionService.createQuestion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new QuestionResponse(newQuestion));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<QuestionResponse>> getAllQuestions() {
        List<Question> questions = questionService.findAllQuestions();
        List<QuestionResponse> response = questions.stream()
                .map(QuestionResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable Long questionId) {
        Question question = questionService.findQuestionById(questionId);
        return ResponseEntity.ok(new QuestionResponse(question));
    }
}
