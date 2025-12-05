package sw2.project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw2.project.common.BaseTimeEntity;

@Entity
@Table(name = "challenge_participants", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"challenge_id", "user_id"})
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeParticipant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @Column(nullable = false, length = 36)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParticipantStatus status;

    public enum ParticipantStatus {
        IN_PROGRESS, COMPLETED, GAVE_UP
    }

    @Builder
    public ChallengeParticipant(Challenge challenge, String userId, ParticipantStatus status) {
        this.challenge = challenge;
        this.userId = userId;
        this.status = status;
    }

    public void updateStatus(ParticipantStatus status) {
        this.status = status;
    }
}
