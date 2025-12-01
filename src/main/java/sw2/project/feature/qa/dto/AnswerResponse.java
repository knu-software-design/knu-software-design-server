package sw2.project.feature.qa.dto;

import lombok.Getter;
import sw2.project.domain.question.Answer;

import java.time.LocalDateTime;

@Getter
public class AnswerResponse {

    private final Long answerId;
    private final String content;
    private final String authorId;
    private final LocalDateTime createdAt;

    public AnswerResponse(Answer answer) {
        this.answerId = answer.getId();
        this.content = answer.getContent();
        this.authorId = answer.getAuthor().getId();
        this.createdAt = answer.getCreatedAt();
    }
}
