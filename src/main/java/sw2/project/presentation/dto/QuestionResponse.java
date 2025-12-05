package sw2.project.presentation.dto;

import lombok.Getter;
import sw2.project.domain.Question;

import java.time.LocalDateTime;

@Getter
public class QuestionResponse {

    private final Long questionId;
    private final String title;
    private final String content;
    private final String authorId;
    private final LocalDateTime createdAt;

    public QuestionResponse(Question question) {
        this.questionId = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.authorId = question.getUserId();
        this.createdAt = question.getCreatedAt();
    }
}
