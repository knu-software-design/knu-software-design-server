package sw2.project.feature.fasttest;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw2.project.feature.fasttest.dto.FastTestRequest;
import sw2.project.feature.fasttest.dto.FastTestResponse;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/fast-test")
@RequiredArgsConstructor
public class FastTestController {

    private final FastTestService fastTestService;

    @PostMapping("/run")
    public ResponseEntity<FastTestResponse> runTest(@Valid @RequestBody FastTestRequest request) {
        JsonNode result = fastTestService.runFastTest(request);
        FastTestResponse response = new FastTestResponse(
                request.getUserId(),
                result,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
}
