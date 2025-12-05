package sw2.project.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sw2.project.domain.Challenge;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("select distinct c from Challenge c left join fetch c.participants")
    List<Challenge> findAllWithParticipants();
}
