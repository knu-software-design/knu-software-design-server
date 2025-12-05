package sw2.project.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import sw2.project.presentation.dto.HealthLogRequest;

@Tag(name = "건강 기록 API", description = "사용자의 건강 데이터를 기록하는 API")
public interface HealthControllerDocs {

    @Operation(summary = "건강 기록 저장", description = "사용자의 혈압, 혈당 등 건강 데이터를 입력 받아 저장합니다.")
    ResponseEntity<String> saveHealthLog(@Valid @RequestBody HealthLogRequest request);
}
