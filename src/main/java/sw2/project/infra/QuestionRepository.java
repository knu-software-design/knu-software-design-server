package sw2.project.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import sw2.project.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
