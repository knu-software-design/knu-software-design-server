package sw2.project.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class FastTestRequest {

    @NotBlank(message = "사용자 ID는 필수입니다.")
    private String userId;

    private String face;
    private String arm;
    private String speech;
    private String time;
}
