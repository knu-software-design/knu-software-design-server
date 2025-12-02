package sw2.project.feature.fasttest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import sw2.project.feature.fasttest.dto.FastTestRequest;
import sw2.project.feature.fasttest.dto.FastTestResponse;

@Tag(name = "FAST 테스트 API", description = "FAST 테스트 실행 및 Mock 분석 결과 조회 API")
public interface FastTestControllerDocs {

    @Operation(summary = "FAST 테스트 실행", description = "사용자 데이터를 기반으로 Mock FAST 테스트를 실행하고 분석 결과를 반환합니다.")
    ResponseEntity<FastTestResponse> runTest(@Valid @RequestBody FastTestRequest request);
}
