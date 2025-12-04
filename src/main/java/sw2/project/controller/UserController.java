package sw2.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw2.project.domain.Gender;
import sw2.project.domain.User;
import sw2.project.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // 테스트용 사용자 생성 API (POST /api/user/init)
    @PostMapping("/init")
    public String createTestUser() {
        // 나이 65세, 남성, 가족력 있음 -> 고위험군 테스트용
        User user = new User("홍길동", 65, Gender.MALE, true);
        userRepository.save(user);
        return "테스트용 사용자(ID: " + user.getId() + ")가 생성되었습니다.";
    }
}