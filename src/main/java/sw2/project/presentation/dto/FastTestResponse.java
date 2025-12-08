package sw2.project.presentation.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FastTestResponse {

    private final String userId;
    private final String testType = "FAST";

    @JsonRawValue
    private final String result;

    private final LocalDateTime generatedAt;

    public FastTestResponse(String userId, String result, LocalDateTime generatedAt) {
        this.userId = userId;
        this.result = result;
        this.generatedAt = generatedAt;
    }
}
