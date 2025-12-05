package sw2.project.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.project.presentation.dto.RiskResponse;
import sw2.project.application.RiskService;

@RestController
@RequestMapping("/api/risk")
@RequiredArgsConstructor
public class RiskController {

    private final RiskService riskService;

    @GetMapping("/{userId}")
    public ResponseEntity<RiskResponse> getRiskAnalysis(@PathVariable Long userId) {
        // 서비스 계층을 호출하여 분석 로직 수행
        RiskResponse response = riskService.analyzeRisk(userId);

        return ResponseEntity.ok(response);
    }
}
