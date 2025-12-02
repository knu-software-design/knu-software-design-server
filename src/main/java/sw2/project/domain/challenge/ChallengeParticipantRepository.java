package sw2.project.domain.challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChallengeParticipantRepository extends JpaRepository<ChallengeParticipant, Long> {
    Optional<ChallengeParticipant> findByChallengeAndUserId(Challenge challenge, String userId);

    @Query("select cp from ChallengeParticipant cp join fetch cp.challenge where cp.userId = :userId")
    List<ChallengeParticipant> findByUserId(@Param("userId") String userId);
}
