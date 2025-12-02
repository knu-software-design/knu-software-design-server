package sw2.project.domain.challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("select distinct c from Challenge c left join fetch c.participants")
    List<Challenge> findAllWithParticipants();
}
