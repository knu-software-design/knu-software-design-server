package sw2.project.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import sw2.project.presentation.dto.RiskResponse;

@Tag(name = "위험도 분석 API", description = "건강 데이터를 기반으로 한 뇌졸중 위험도 분석 API")
public interface RiskControllerDocs {

    @Operation(summary = "위험도 분석", description = "사용자의 최신 건강 기록을 분석하여 위험 등급과 주요 피드백을 제공합니다.")
    ResponseEntity<RiskResponse> getRiskAnalysis(
            @Parameter(description = "위험도를 분석할 사용자 ID") @PathVariable Long userId);
}
