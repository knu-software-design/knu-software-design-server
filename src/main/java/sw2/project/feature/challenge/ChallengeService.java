package sw2.project.feature.challenge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw2.project.domain.challenge.Challenge;
import sw2.project.domain.challenge.ChallengeParticipant;
import sw2.project.domain.challenge.ChallengeParticipant.ParticipantStatus;
import sw2.project.domain.challenge.ChallengeParticipantRepository;
import sw2.project.domain.challenge.ChallengeRepository;
import sw2.project.feature.challenge.dto.ChallengeProgressRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeParticipantRepository participantRepository;

    /**
     * 모든 챌린지 목록을 조회합니다.
     */
    public List<Challenge> findAllChallenges() {
        return challengeRepository.findAllWithParticipants();
    }

    /**
     * 사용자가 특정 챌린지에 참여합니다.
     */
    @Transactional
    public ChallengeParticipant joinChallenge(Long challengeId, String userId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 챌린지를 찾을 수 없습니다: " + challengeId));

        // 이미 참여했는지 확인
        Optional<ChallengeParticipant> existingParticipant = participantRepository.findByChallengeAndUserId(challenge, userId);
        if (existingParticipant.isPresent()) {
            throw new IllegalStateException("이미 참여중인 챌린지입니다.");
        }

        ChallengeParticipant participant = ChallengeParticipant.builder()
                .challenge(challenge)
                .userId(userId)
                .status(ParticipantStatus.IN_PROGRESS)
                .build();

        return participantRepository.save(participant);
    }

    /**
     * 사용자가 참여중인 챌린지 목록을 조회합니다.
     */
    public List<ChallengeParticipant> findChallengesByUserId(String userId) {
        return participantRepository.findByUserId(userId);
    }

    /**
     * 챌린지 진행률을 업데이트합니다. (여기서는 1회성 성공으로 가정)
     */
    @Transactional
    public ChallengeParticipant updateChallengeProgress(ChallengeProgressRequest request) {
        Challenge challenge = challengeRepository.findById(request.getChallengeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 챌린지를 찾을 수 없습니다: " + request.getChallengeId()));

        ChallengeParticipant participant = participantRepository.findByChallengeAndUserId(challenge, request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 챌린지에 참여하고 있지 않습니다."));

        if (participant.getStatus() == ParticipantStatus.COMPLETED) {
            throw new IllegalStateException("이미 완료한 챌린지입니다.");
        }

        // 진행률 업데이트 로직 (예: 상태를 COMPLETED로 변경)
        // 실제 서비스에서는 단계(step)를 증가시키고, 목표치와 비교하여 완료 여부를 결정할 수 있습니다.
        participant.updateStatus(ParticipantStatus.COMPLETED);

        return participant;
    }
}
