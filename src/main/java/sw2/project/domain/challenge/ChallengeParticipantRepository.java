package sw2.project.domain.challenge;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeParticipantRepository extends JpaRepository<ChallengeParticipant, Long> {
    Optional<ChallengeParticipant> findByChallengeAndUserId(Challenge challenge, String userId);
    List<ChallengeParticipant> findByUserId(String userId);
}
