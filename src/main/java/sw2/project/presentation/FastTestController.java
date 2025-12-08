package sw2.project.presentation;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw2.project.application.FastTestService;
import sw2.project.presentation.dto.FastTestRequest;
import sw2.project.presentation.dto.FastTestResponse;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/fast-test")
@RequiredArgsConstructor
public class FastTestController implements FastTestControllerDocs {

    private final FastTestService fastTestService;

    @Override
    @PostMapping("/run")
    public ResponseEntity<FastTestResponse> runTest(@Valid @RequestBody FastTestRequest request) {
        JsonNode result = fastTestService.runFastTest(request);
        FastTestResponse response = new FastTestResponse(
                request.getUserId(),
                result.toString(),
                LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
