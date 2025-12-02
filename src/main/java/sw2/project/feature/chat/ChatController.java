package sw2.project.feature.chat;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw2.project.feature.chat.dto.ChatRequest;
import sw2.project.feature.chat.dto.ChatResponse;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponse> handleChat(@Valid @RequestBody ChatRequest chatRequest) {
        try {
            String reply = chatService.getAiReply(chatRequest);
            return ResponseEntity.ok(new ChatResponse(reply));
        } catch (Exception e) {
            // 간단한 예외 처리
            // GlobalExceptionHandler를 통해 더 정교하게 처리할 수 있습니다.
            return ResponseEntity.internalServerError()
                    .body(new ChatResponse("죄송합니다. 메시지 처리 중 오류가 발생했습니다."));
        }
    }
}
