package sw2.project.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw2.project.domain.Routine;
import sw2.project.domain.User;
import sw2.project.infra.RoutineRepository;
import sw2.project.infra.UserRepository;
import sw2.project.presentation.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final UserRepository userRepository;

    public RoutineResponse createRoutine(Long userId, RoutineCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Routine routine = Routine.builder()
                .user(user)
                .title(request.getTitle())
                .type(request.getType())
                .timeOfDay(request.getTimeOfDay())
                .completed(false)
                .build();

        Routine saved = routineRepository.save(routine);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<RoutineResponse> getRoutines(Long userId) {
        return routineRepository.findByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public RoutineResponse updateRoutineStatus(Long routineId, RoutineStatusUpdateRequest request) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalArgumentException("루틴을 찾을 수 없습니다."));
        routine.setCompleted(Boolean.TRUE.equals(request.getCompleted()));
        return toResponse(routine);
    }

    @Transactional(readOnly = true)
    public RoutineNotificationResponse getMockNotifications(Long userId) {
        List<String> messages = routineRepository.findByUserId(userId).stream()
                .filter(routine -> !routine.isCompleted())
                .map(routine -> String.format("%s - [%s] %s을(를) 아직 완료하지 않았습니다.",
                        routine.getTimeOfDay(), routine.getType(), routine.getTitle()))
                .collect(Collectors.toList());

        return RoutineNotificationResponse.builder()
                .userId(userId)
                .messages(messages)
                .build();
    }

    private RoutineResponse toResponse(Routine routine) {
        return RoutineResponse.builder()
                .id(routine.getId())
                .title(routine.getTitle())
                .type(routine.getType())
                .timeOfDay(routine.getTimeOfDay())
                .completed(routine.isCompleted())
                .build();
    }
}
