package sw2.project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw2.project.common.BaseTimeEntity;

@Entity
@Table(name = "chat_histories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String userId;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String userMessage;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String aiResponse;

    @Builder
    public ChatHistory(String userId, String userMessage, String aiResponse) {
        this.userId = userId;
        this.userMessage = userMessage;
        this.aiResponse = aiResponse;
    }
}
