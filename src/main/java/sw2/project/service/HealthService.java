package sw2.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw2.project.domain.HealthLog;
import sw2.project.domain.User;
import sw2.project.dto.HealthLogRequest;
import sw2.project.repository.HealthLogRepository;
import sw2.project.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class HealthService {

    private final HealthLogRepository healthLogRepository;
    private final UserRepository userRepository;

    public Long saveHealthLog(HealthLogRequest request) {
        // 1. 사용자 조회
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 2. DTO -> Entity 변환 (Builder 패턴 사용 권장)
        HealthLog healthLog = HealthLog.builder()
                .user(user)
                .systolicBp(request.getSystolicBp())
                .diastolicBp(request.getDiastolicBp())
                .bloodSugar(request.getBloodSugar())
                .exerciseMinutes(request.getExerciseMinutes())
                .sodiumIntake(request.getSodiumEstimate()) // 필요 시 추가
                .build();

        HealthLog savedLog = healthLogRepository.save(healthLog);

        return savedLog.getId();
    }
}