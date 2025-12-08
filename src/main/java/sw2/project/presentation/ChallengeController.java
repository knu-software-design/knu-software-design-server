package sw2.project.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.project.application.ChallengeService;
import sw2.project.domain.Challenge;
import sw2.project.domain.ChallengeParticipant;
import sw2.project.presentation.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChallengeController implements ChallengeControllerDocs {

    private final ChallengeService challengeService;

    @Override
    @GetMapping("/challenges")
    public ResponseEntity<List<ChallengeResponse>> getAllChallenges() {
        List<Challenge> challenges = challengeService.findAllChallenges();
        List<ChallengeResponse> response = challenges.stream()
                .map(ChallengeResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/challenges")
    public ResponseEntity<String> createChallenge(@Valid @RequestBody ChallengeCreateRequest request) {
        challengeService.createChallenge(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("챌린지가 생성되었습니다.");
    }

    @Override
    @PostMapping("/challenges/{challengeId}/join")
    public ResponseEntity<String> joinChallenge(
            @PathVariable Long challengeId,
            @Valid @RequestBody ChallengeJoinRequest request) {
        challengeService.joinChallenge(challengeId, request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body("챌린지 참여가 완료되었습니다.");
    }

    @Override
    @GetMapping("/users/{userId}/challenges")
    public ResponseEntity<List<UserChallengeResponse>> getUserChallenges(@PathVariable String userId) {
        List<ChallengeParticipant> participants = challengeService.findChallengesByUserId(userId);
        List<UserChallengeResponse> response = participants.stream()
                .map(UserChallengeResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping("/challenges/progress")
    public ResponseEntity<String> updateProgress(@Valid @RequestBody ChallengeProgressRequest request) {
        challengeService.updateChallengeProgress(request);
        return ResponseEntity.ok("진행률이 업데이트되었습니다.");
    }
}
