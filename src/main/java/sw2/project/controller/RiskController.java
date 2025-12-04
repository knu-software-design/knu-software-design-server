package sw2.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.project.dto.RiskResponse;
import sw2.project.service.RiskService;

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