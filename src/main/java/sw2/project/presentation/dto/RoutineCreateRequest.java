package sw2.project.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineCreateRequest {

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "루틴 타입은 필수입니다.")
    private String type;

    @NotBlank(message = "시간 정보는 필수입니다.")
    private String timeOfDay;
}
