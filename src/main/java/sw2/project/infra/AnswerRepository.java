package sw2.project.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import sw2.project.domain.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);
}
