package sw2.project.feature.challenge;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.project.domain.challenge.Challenge;
import sw2.project.domain.challenge.ChallengeParticipant;
import sw2.project.feature.challenge.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    // 챌린지 전체 목록 조회
    @GetMapping("/challenges")
    public ResponseEntity<List<ChallengeResponse>> getAllChallenges() {
        List<Challenge> challenges = challengeService.findAllChallenges();
        List<ChallengeResponse> response = challenges.stream()
                .map(ChallengeResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // 챌린지 참여
    @PostMapping("/challenges/{challengeId}/join")
    public ResponseEntity<String> joinChallenge(
            @PathVariable Long challengeId,
            @Valid @RequestBody ChallengeJoinRequest request) {
        challengeService.joinChallenge(challengeId, request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body("챌린지 참여가 완료되었습니다.");
    }

    // 특정 사용자가 참여한 챌린지 목록 조회
    @GetMapping("/users/{userId}/challenges")
    public ResponseEntity<List<UserChallengeResponse>> getUserChallenges(@PathVariable String userId) {
        List<ChallengeParticipant> participants = challengeService.findChallengesByUserId(userId);
        List<UserChallengeResponse> response = participants.stream()
                .map(UserChallengeResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // 챌린지 진행률 업데이트
    @PostMapping("/challenges/progress")
    public ResponseEntity<String> updateProgress(@Valid @RequestBody ChallengeProgressRequest request) {
        challengeService.updateChallengeProgress(request);
        return ResponseEntity.ok("진행률이 업데이트되었습니다.");
    }
}
