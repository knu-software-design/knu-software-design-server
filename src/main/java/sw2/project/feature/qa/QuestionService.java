package sw2.project.feature.qa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw2.project.domain.question.Answer;
import sw2.project.domain.question.AnswerRepository;
import sw2.project.domain.question.Question;
import sw2.project.domain.question.QuestionRepository;
import sw2.project.domain.user.User;
import sw2.project.domain.user.UserRepository;
import sw2.project.feature.qa.dto.AnswerCreateRequest;
import sw2.project.feature.qa.dto.QuestionCreateRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    @Transactional
    public Question createQuestion(QuestionCreateRequest request) {
        User author = findOrCreateUser(request.getUserId());
        Question question = Question.builder()
                .author(author)
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
        User author = findOrCreateUser(request.getUserId());
        Question question = findQuestionById(questionId);
        Answer answer = Answer.builder()
                .author(author)
                .question(question)
                .content(request.getContent())
                .build();
        answer.setQuestion(question);
        return answerRepository.save(answer);
    }

    private User findOrCreateUser(String userId) {
        return userRepository.findById(userId)
                .orElseGet(() -> userRepository.save(new User(userId)));
    }
}
