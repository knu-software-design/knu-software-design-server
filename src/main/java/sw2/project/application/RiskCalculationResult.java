package sw2.project.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sw2.project.global.RiskLevel;

@Getter
@AllArgsConstructor
public class RiskCalculationResult {

    private final int totalScore;
    private final RiskLevel riskLevel;
}
