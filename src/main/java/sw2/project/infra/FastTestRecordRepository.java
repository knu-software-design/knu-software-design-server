package sw2.project.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import sw2.project.domain.FastTestRecord;

public interface FastTestRecordRepository extends JpaRepository<FastTestRecord, Long> {
}
