package sw2.project.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SimulationYearResult {

    private final int year;
    private final double riskScore;
    private final String riskGrade;
}
