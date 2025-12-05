package sw2.project.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import sw2.project.global.RiskLevel;

@Data
@AllArgsConstructor
public class RiskResponse {
    private Long userId;

    private RiskLevel riskLevel;

    private int totalScore;
    private String MajorRiskFactor;
    private String message;
}
