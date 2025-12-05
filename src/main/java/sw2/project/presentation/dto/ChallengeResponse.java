package sw2.project.presentation.dto;

import lombok.Getter;
import sw2.project.domain.Challenge;

@Getter
public class ChallengeResponse {
    private final Long id;
    private final String title;
    private final String description;
    private final int participantCount;

    public ChallengeResponse(Challenge challenge) {
        this.id = challenge.getId();
        this.title = challenge.getTitle();
        this.description = challenge.getDescription();
        this.participantCount = challenge.getParticipants().size();
    }
}
