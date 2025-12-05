package sw2.project.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sw2.project.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
