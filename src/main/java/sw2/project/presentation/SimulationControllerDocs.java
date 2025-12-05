package sw2.project.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import sw2.project.presentation.dto.SimulationResponse;

@Tag(name = "미래 시뮬레이션 API", description = "생활 습관 시나리오별 위험도 변화를 조회하는 API")
public interface SimulationControllerDocs {

    @Operation(summary = "미래 시뮬레이션 조회", description = "KEEP/IMPROVE 두 시나리오의 1·5·10년 후 위험도를 계산합니다.")
    ResponseEntity<SimulationResponse> getSimulation(
            @Parameter(description = "시뮬레이션을 조회할 사용자 ID") @PathVariable Long userId);
}
