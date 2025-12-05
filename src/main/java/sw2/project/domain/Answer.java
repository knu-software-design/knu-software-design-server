package sw2.project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw2.project.common.BaseTimeEntity;

@Entity
@Table(name = "answers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Answer(String userId, Question question, String content) {
        this.userId = userId;
        this.question = question;
        this.content = content;
    }
}
