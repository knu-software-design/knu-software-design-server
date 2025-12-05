package sw2.project.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "사용자 API", description = "사용자 관리 및 초기화 관련 API")
public interface UserControllerDocs {

    @Operation(summary = "테스트 사용자 생성", description = "데모용 고위험군 사용자를 생성하여 다른 API 테스트에 활용합니다.")
    ResponseEntity<String> createTestUser();
}
