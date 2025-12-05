package sw2.project.presentation.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoutineNotificationResponse {

    private final Long userId;
    private final List<String> messages;
}
