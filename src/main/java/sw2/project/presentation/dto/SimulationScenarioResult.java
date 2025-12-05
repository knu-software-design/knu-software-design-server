package sw2.project.presentation.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SimulationScenarioResult {

    private final String scenario;
    private final List<SimulationYearResult> timeline;
}
