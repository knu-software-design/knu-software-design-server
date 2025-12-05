package sw2.project.presentation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HealthLogRequest {
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;

    @Min(value = 0, message = "혈압은 0 이상이어야 합니다.")
    @Max(value = 300, message = "혈압 수치가 비정상적으로 높습니다(300 초과).")
    private Integer systolicBp;

    @Min(value = 0) @Max(value = 300)
    private Integer diastolicBp;

    @Min(value = 0, message = "운동 시간은 음수일 수 없습니다.")
    private Integer exerciseMinutes;

    private Integer bloodSugar;
    private String dietText;
    private Integer sodiumEstimate;
}
