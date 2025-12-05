package sw2.project.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import sw2.project.presentation.dto.ChatRequest;
import sw2.project.presentation.dto.ChatResponse;

@Tag(name = "챗봇 API", description = "대화형 코칭 챗봇 API")
public interface ChatControllerDocs {

    @Operation(summary = "챗봇 메시지 전송", description = "사용자가 메시지를 보내면 OpenAI API를 호출하여 얻은 AI의 응답을 반환하고 대화 내용을 저장합니다.")
    ResponseEntity<ChatResponse> handleChat(@Valid @RequestBody ChatRequest chatRequest);
}
