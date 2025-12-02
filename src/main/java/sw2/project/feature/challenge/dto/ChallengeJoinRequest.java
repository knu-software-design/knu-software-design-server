package sw2.project.feature.challenge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChallengeJoinRequest {
    @NotBlank(message = "사용자 ID는 필수입니다.")
    private String userId;
}
