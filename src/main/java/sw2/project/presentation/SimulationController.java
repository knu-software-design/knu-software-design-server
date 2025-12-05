package sw2.project.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw2.project.application.SimulationService;
import sw2.project.presentation.dto.SimulationResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/simulation")
public class SimulationController implements SimulationControllerDocs {

    private final SimulationService simulationService;

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<SimulationResponse> getSimulation(@PathVariable Long userId) {
        return ResponseEntity.ok(simulationService.simulateFuture(userId));
    }
}
