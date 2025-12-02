package sw2.project.feature.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw2.project.domain.chathistory.ChatHistory;
import sw2.project.domain.chathistory.ChatHistoryRepository;
import sw2.project.feature.chat.dto.ChatRequest;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatHistoryRepository chatHistoryRepository;

    /**
     * 사용자의 메시지를 받아 AI의 응답을 반환하고, 대화 내용을 저장합니다.
     */
    @Transactional
    public String getAiReply(ChatRequest request) {
        // 1. AI 응답 생성 (Mock)
        String aiReply = generateMockAiReply(request.getMessage());

        // 2. 대화 기록 생성 및 저장
        ChatHistory history = ChatHistory.builder()
                .userId(request.getUserId())
                .userMessage(request.getMessage())
                .aiResponse(aiReply)
                .build();
        
        chatHistoryRepository.save(history);

        // 3. AI 응답 반환
        return aiReply;
    }

    /**
     * LLM 호출을 대체하는 Mock 메서드
     * @param userMessage 사용자의 메시지
     * @return 간단한 규칙에 따른 모의 AI 응답
     */
    private String generateMockAiReply(String userMessage) {
        if (userMessage == null || userMessage.isBlank()) {
            return "무슨 말씀이신지 잘 모르겠어요. 조금 더 자세히 말씀해주시겠어요?";
        }

        if (userMessage.contains("안녕")) {
            return "안녕하세요! 무엇을 도와드릴까요?";
        } else if (userMessage.contains("이름")) {
            return "저는 대화를 돕는 AI 챗봇입니다.";
        } else if (userMessage.contains("피곤") || userMessage.contains("힘들")) {
            return "많이 지치셨군요. 잠시 하던 일을 멈추고 따뜻한 차 한잔의 여유를 가져보는 건 어떨까요?";
        } else {
            return "흥미로운 이야기네요. 조금 더 듣고 싶어요.";
        }
    }
}
