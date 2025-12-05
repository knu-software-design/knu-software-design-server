package sw2.project.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw2.project.domain.SimulationScenarioType;
import sw2.project.domain.User;
import sw2.project.infra.HealthLogRepository;
import sw2.project.infra.UserRepository;
import sw2.project.presentation.dto.SimulationResponse;
import sw2.project.presentation.dto.SimulationScenarioResult;
import sw2.project.presentation.dto.SimulationYearResult;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SimulationService {

    private final UserRepository userRepository;
    private final HealthLogRepository healthLogRepository;
    private final RiskService riskService;

    public SimulationResponse simulateFuture(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        double baselineScore = healthLogRepository.findTop1ByUser_IdOrderByDateDesc(userId).isPresent()
                ? riskService.calculateRiskResult(userId).getTotalScore()
                : 50.0;

        List<SimulationYearResult> keepTimeline = List.of(
                buildYearResult(1, baselineScore + 5),
                buildYearResult(5, baselineScore + 15),
                buildYearResult(10, baselineScore + 25)
        );

        List<SimulationYearResult> improveTimeline = List.of(
                buildYearResult(1, baselineScore - 5),
                buildYearResult(5, baselineScore - 15),
                buildYearResult(10, baselineScore - 25)
        );

        SimulationScenarioResult keep = buildScenario(SimulationScenarioType.KEEP, keepTimeline);
        SimulationScenarioResult improve = buildScenario(SimulationScenarioType.IMPROVE, improveTimeline);

        return SimulationResponse.builder()
                .userId(user.getId())
                .scenarios(List.of(keep, improve))
                .build();
    }

    private SimulationScenarioResult buildScenario(SimulationScenarioType type, List<SimulationYearResult> timeline) {
        return SimulationScenarioResult.builder()
                .scenario(type.name())
                .timeline(timeline)
                .build();
    }

    private SimulationYearResult buildYearResult(int year, double rawScore) {
        double score = Math.max(0, Math.min(100, rawScore));
        return SimulationYearResult.builder()
                .year(year)
                .riskScore(score)
                .riskGrade(toGrade(score))
                .build();
    }

    private String toGrade(double score) {
        if (score <= 33) {
            return "GREEN";
        } else if (score <= 66) {
            return "YELLOW";
        }
        return "RED";
    }
}
