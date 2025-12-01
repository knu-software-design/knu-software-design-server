package sw2.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sw2.project.domain.HealthLog;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthLogRepository extends JpaRepository<HealthLog,Long> {
    List<HealthLog> findByUser_IdOrderByDateDesc(Long userId);
    Optional<HealthLog> findTop1ByUser_IdOrderByDateDesc(Long userId);
}
