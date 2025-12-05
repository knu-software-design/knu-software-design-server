package sw2.project.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoutineResponse {

    private final Long id;
    private final String title;
    private final String type;
    private final String timeOfDay;
    private final boolean completed;
}
