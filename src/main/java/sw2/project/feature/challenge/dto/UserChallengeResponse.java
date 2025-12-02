package sw2.project.feature.challenge.dto;

import lombok.Getter;
import sw2.project.domain.challenge.ChallengeParticipant;
import sw2.project.domain.challenge.ChallengeParticipant.ParticipantStatus;

@Getter
public class UserChallengeResponse {
    private final Long challengeId;
    private final String title;
    private final ParticipantStatus status;
    // 추가적으로 진행률, 남은 기간 등 필드 확장 가능

    public UserChallengeResponse(ChallengeParticipant participant) {
        this.challengeId = participant.getChallenge().getId();
        this.title = participant.getChallenge().getTitle();
        this.status = participant.getStatus();
    }
}
