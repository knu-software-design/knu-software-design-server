package sw2.project.feature.fasttest.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FastTestResponse {

    private final String userId;
    private final String testType = "FAST";
    private final JsonNode result;
    private final LocalDateTime generatedAt;

    public FastTestResponse(String userId, JsonNode result, LocalDateTime generatedAt) {
        this.userId = userId;
        this.result = result;
        this.generatedAt = generatedAt;
    }
}
