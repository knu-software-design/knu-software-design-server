package sw2.project.feature.challenge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChallengeProgressRequest {
    @NotBlank(message = "사용자 ID는 필수입니다.")
    private String userId;

    @NotNull(message = "챌린지 ID는 필수입니다.")
    private Long challengeId;
}
