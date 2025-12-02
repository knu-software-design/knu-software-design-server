package sw2.project.feature.challenge;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sw2.project.feature.challenge.dto.ChallengeJoinRequest;
import sw2.project.feature.challenge.dto.ChallengeProgressRequest;
import sw2.project.feature.challenge.dto.ChallengeResponse;
import sw2.project.feature.challenge.dto.UserChallengeResponse;

import java.util.List;

@Tag(name = "챌린지 API", description = "그룹 챌린지 기능 API")
public interface ChallengeControllerDocs {

    @Operation(summary = "전체 챌린지 목록 조회", description = "참여 가능한 모든 챌린지의 목록을 조회합니다.")
    ResponseEntity<List<ChallengeResponse>> getAllChallenges();

    @Operation(summary = "챌린지 참여", description = "사용자가 특정 챌린지에 참여합니다.")
    ResponseEntity<String> joinChallenge(
            @Parameter(description = "참여할 챌린지의 ID") @PathVariable Long challengeId,
            @Valid @RequestBody ChallengeJoinRequest request);

    @Operation(summary = "사용자별 참여 챌린지 조회", description = "특정 사용자가 참여하고 있는 챌린지 목록을 조회합니다.")
    ResponseEntity<List<UserChallengeResponse>> getUserChallenges(
            @Parameter(description = "조회할 사용자의 ID") @PathVariable String userId);

    @Operation(summary = "챌린지 진행률 업데이트", description = "챌린지 진행 상태를 업데이트합니다. (현재 구현은 '완료' 상태로 변경)")
    ResponseEntity<String> updateProgress(@Valid @RequestBody ChallengeProgressRequest request);
}
