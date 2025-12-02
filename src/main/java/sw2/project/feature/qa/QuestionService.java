package sw2.project.feature.qa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw2.project.domain.question.Answer;
import sw2.project.domain.question.AnswerRepository;
import sw2.project.domain.question.Question;
import sw2.project.domain.question.QuestionRepository;
import sw2.project.feature.qa.dto.AnswerCreateRequest;
import sw2.project.feature.qa.dto.QuestionCreateRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public Question createQuestion(QuestionCreateRequest request) {
        Question question = Question.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        return questionRepository.save(question);
    }

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found with id: " + questionId));
    }

    @Transactional
    public Answer createAnswer(Long questionId, AnswerCreateRequest request) {
        Question question = findQuestionById(questionId);
        Answer answer = Answer.builder()
                .userId(request.getUserId())
                .question(question)
                .content(request.getContent())
                .build();
        return answerRepository.save(answer);
    }
}
