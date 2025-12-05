package sw2.project.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import sw2.project.domain.Routine;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findByUserId(Long userId);
}
