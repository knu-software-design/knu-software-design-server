package sw2.project.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sw2.project.presentation.dto.*;

import java.util.List;

@Tag(name = "루틴/알림 API", description = "건강 루틴 관리 및 모의 알림 API")
public interface RoutineControllerDocs {

    @Operation(summary = "루틴 생성", description = "사용자에게 새로운 건강 루틴을 추가합니다.")
    ResponseEntity<RoutineResponse> createRoutine(
            @Parameter(description = "루틴을 생성할 사용자 ID") @PathVariable Long userId,
            @Valid @RequestBody RoutineCreateRequest request);

    @Operation(summary = "루틴 목록 조회", description = "사용자의 등록된 루틴을 모두 조회합니다.")
    ResponseEntity<List<RoutineResponse>> getRoutines(
            @Parameter(description = "루틴을 조회할 사용자 ID") @PathVariable Long userId);

    @Operation(summary = "루틴 완료 여부 업데이트", description = "특정 루틴의 완료 여부를 갱신합니다.")
    ResponseEntity<RoutineResponse> updateRoutineStatus(
            @Parameter(description = "루틴 소유 사용자 ID") @PathVariable Long userId,
            @Parameter(description = "상태를 변경할 루틴 ID") @PathVariable Long routineId,
            @Valid @RequestBody RoutineStatusUpdateRequest request);

    @Operation(summary = "루틴 알림(mock)", description = "미완료 루틴을 기반으로 발송될 가상 알림 문구를 제공합니다.")
    ResponseEntity<RoutineNotificationResponse> getMockNotifications(
            @Parameter(description = "알림을 확인할 사용자 ID") @PathVariable Long userId);
}
