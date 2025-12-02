package sw2.project.feature.qa.dto;

import lombok.Getter;
import sw2.project.domain.question.Question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QuestionResponse {

    private final Long questionId;
    private final String title;
    private final String content;
    private final String authorId;
    private final LocalDateTime createdAt;
    private final List<AnswerResponse> answers;

    public QuestionResponse(Question question) {
        this.questionId = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.authorId = question.getUserId();
        this.createdAt = question.getCreatedAt();
        this.answers = question.getAnswers().stream()
                .map(AnswerResponse::new)
                .collect(Collectors.toList());
    }
}
