package sw2.project.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import sw2.project.domain.ChatHistory;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {
}
