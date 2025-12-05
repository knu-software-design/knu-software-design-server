package sw2.project.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw2.project.domain.Gender;
import sw2.project.domain.User;
import sw2.project.infra.UserRepository;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController implements UserControllerDocs {

    private final UserRepository userRepository;

    // 테스트용 사용자 생성 API (POST /api/user/init)
    @Override
    @PostMapping("/init")
    public ResponseEntity<String> createTestUser() {
        // 나이 65세, 남성, 가족력 있음 -> 고위험군 테스트용
        User user = new User("홍길동", 65, Gender.MALE, true);
        userRepository.save(user);
        return ResponseEntity.ok("테스트용 사용자(ID: " + user.getId() + ")가 생성되었습니다.");
    }
}
