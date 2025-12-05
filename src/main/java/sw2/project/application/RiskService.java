package sw2.project.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw2.project.domain.HealthLog;
import sw2.project.domain.User;
import sw2.project.global.RiskLevel;
import sw2.project.infra.HealthLogRepository;
import sw2.project.infra.UserRepository;
import sw2.project.presentation.dto.RiskResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 조회 전용이므로 성능 최적화
public class RiskService {

    private final HealthLogRepository healthLogRepository;
    private final UserRepository userRepository;

    /**
     * 사용자의 최신 건강 데이터를 기반으로 뇌졸중 위험도를 분석합니다.
     */
    public RiskResponse analyzeRisk(Long userId) {
        // 1. 사용자 및 최신 건강 기록 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        HealthLog latestLog = healthLogRepository.findTop1ByUser_IdOrderByDateDesc(userId)
                .orElseThrow(() -> new IllegalArgumentException("분석할 건강 데이터가 없습니다. 먼저 데이터를 입력해주세요."));

        RiskCalculationResult calculationResult = buildRiskCalculation(user, latestLog);

        // 4. 주요 위험 요인 분석 (피드백 메시지 생성)
        String feedback = generateFeedback(user, latestLog, calculationResult.getRiskLevel());

        return new RiskResponse(
                userId,
                calculationResult.getRiskLevel(),
                calculationResult.getTotalScore(),
                "복합 요인",
                feedback
        );
    }

    /**
     * 위험도 점수와 등급을 함께 반환하는 보조 메서드 (시뮬레이션 등에서 활용).
     */
    public RiskCalculationResult calculateRiskResult(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        HealthLog latestLog = healthLogRepository.findTop1ByUser_IdOrderByDateDesc(userId)
                .orElseThrow(() -> new IllegalArgumentException("분석할 건강 데이터가 없습니다. 먼저 데이터를 입력해주세요."));
        return buildRiskCalculation(user, latestLog);
    }

    private RiskCalculationResult buildRiskCalculation(User user, HealthLog log) {
        int totalScore = calculateDemoScore(user, log);
        RiskLevel riskLevel = determineRiskLevel(totalScore);
        return new RiskCalculationResult(totalScore, riskLevel);
    }

    // [로직] 데모용 점수 계산기
    // SRS 역할 A 문서: "수축기 혈압 140 이상 -> +10" 등 [cite: 148]
    private int calculateDemoScore(User user, HealthLog log) {
        int score = 0;

        // 1. 혈압 요인 (가중치 높음)
        if (log.getSystolicBp() >= 140 || log.getDiastolicBp() >= 90) {
            score += 40; // 고혈압
        } else if (log.getSystolicBp() >= 130) { // 130도 주의 단계
            score += 20;
        }

        // 2. 운동 요인
        if (log.getExerciseMinutes() < 30) {
            score += 15; // 운동 부족
        }

        // 3. 가족력 요인 (User 정보 활용)
        if (user.isHasFamilyHistory()) {
            score += 15;
        }

        // 4. 나이 요인
        if (user.getAge() >= 60) {
            score += 10;
        }

        return score; // 최대 100점이라 가정 시
    }

    // [로직] 점수 -> 등급 변환
    private RiskLevel determineRiskLevel(int score) {
        if (score >= 60) return RiskLevel.DANGER;   // 위험
        if (score >= 40) return RiskLevel.WARNING;  // 경고
        if (score >= 20) return RiskLevel.CAUTION;  // 주의
        return RiskLevel.SAFE;                      // 안전
    }

    // [로직] 피드백 메시지 생성
    private String generateFeedback(User user, HealthLog log, RiskLevel level) {
        if (level == RiskLevel.SAFE) {
            return "현재 건강한 상태를 유지하고 계십니다! 꾸준한 운동을 계속하세요.";
        }

        StringBuilder sb = new StringBuilder();
        if (log.getSystolicBp() >= 130) sb.append("혈압 관리가 시급합니다. ");
        if (log.getExerciseMinutes() < 30) sb.append("운동량을 하루 30분 이상으로 늘리세요. ");
        if (user.isHasFamilyHistory()) sb.append("가족력이 있으므로 정기 검진이 필수입니다.");

        return sb.toString();
    }
}
