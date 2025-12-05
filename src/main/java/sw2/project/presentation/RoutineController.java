package sw2.project.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.project.application.RoutineService;
import sw2.project.presentation.dto.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/routines")
public class RoutineController implements RoutineControllerDocs {

    private final RoutineService routineService;

    @Override
    @PostMapping
    public ResponseEntity<RoutineResponse> createRoutine(
            @PathVariable Long userId,
            @Valid @RequestBody RoutineCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(routineService.createRoutine(userId, request));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RoutineResponse>> getRoutines(@PathVariable Long userId) {
        return ResponseEntity.ok(routineService.getRoutines(userId));
    }

    @Override
    @PatchMapping("/{routineId}/status")
    public ResponseEntity<RoutineResponse> updateRoutineStatus(
            @PathVariable Long userId,
            @PathVariable Long routineId,
            @Valid @RequestBody RoutineStatusUpdateRequest request
    ) {
        return ResponseEntity.ok(routineService.updateRoutineStatus(routineId, request));
    }

    @Override
    @GetMapping("/notifications/mock")
    public ResponseEntity<RoutineNotificationResponse> getMockNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(routineService.getMockNotifications(userId));
    }
}
