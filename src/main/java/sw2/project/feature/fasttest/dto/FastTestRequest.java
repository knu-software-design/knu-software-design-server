package sw2.project.feature.fasttest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class FastTestRequest {

    @NotBlank(message = "사용자 ID는 필수입니다.")
    private String userId;

    // FAST 테스트에 필요한 사용자 입력 데이터 (가변적일 수 있으므로 Map 사용)
    private Map<String, Object> userInput;
}
