package sw2.project.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw2.project.application.HealthService;
import sw2.project.presentation.dto.HealthLogRequest;

@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class HealthController implements HealthControllerDocs {

    private final HealthService healthService;

    @Override
    @PostMapping("/log")
    public ResponseEntity<String> saveHealthLog(@Valid @RequestBody HealthLogRequest request) {
        Long savedId = healthService.saveHealthLog(request);

        return ResponseEntity.ok("건강 데이터가 성공적으로 저장되었습니다. (ID: " + savedId + ")");
    }
}
