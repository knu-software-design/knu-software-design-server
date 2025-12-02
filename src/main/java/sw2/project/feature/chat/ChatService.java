package sw2.project.feature.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sw2.project.domain.chathistory.ChatHistory;
import sw2.project.domain.chathistory.ChatHistoryRepository;
import sw2.project.extern.openai.dto.OpenAIChatRequest;
import sw2.project.extern.openai.dto.OpenAIChatResponse;
import sw2.project.feature.chat.dto.ChatRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatHistoryRepository chatHistoryRepository;
    private final RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    @Transactional
    public String getAiReply(ChatRequest request) {
        // 1. OpenAI API 요청 생성
        OpenAIChatRequest.Message userMessage = new OpenAIChatRequest.Message("user", request.getMessage());
        OpenAIChatRequest openAIRequest = OpenAIChatRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(List.of(userMessage))
                .maxTokens(150)
                .build();

        // 2. HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        // 3. API 호출
        HttpEntity<OpenAIChatRequest> entity = new HttpEntity<>(openAIRequest, headers);
        OpenAIChatResponse openAIResponse = restTemplate.postForObject(OPENAI_API_URL, entity, OpenAIChatResponse.class);

        String aiReply = (openAIResponse != null) ? openAIResponse.firstContent() : "죄송합니다, 응답을 생성할 수 없습니다.";

        // 4. 대화 기록 생성 및 저장
        ChatHistory history = ChatHistory.builder()
                .userId(request.getUserId())
                .userMessage(request.getMessage())
                .aiResponse(aiReply)
                .build();
        chatHistoryRepository.save(history);

        // 5. AI 응답 반환
        return aiReply;
    }
}
