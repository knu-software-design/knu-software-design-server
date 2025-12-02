package sw2.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.project.dto.HealthLogRequest;
import sw2.project.service.HealthService;

@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class HealthController {

    private final HealthService healthService;

    @PostMapping("/log")
    public ResponseEntity<String> saveHealthLog(@RequestBody HealthLogRequest request) {
        Long savedId = healthService.saveHealthLog(request);

        return ResponseEntity.ok("건강 데이터가 성공적으로 저장되었습니다. (ID: " + savedId + ")");
    }
}