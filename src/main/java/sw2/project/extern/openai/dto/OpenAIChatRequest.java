package sw2.project.extern.openai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OpenAIChatRequest {

    private final String model;
    private final List<Message> messages;
    @JsonProperty("max_tokens")
    private final Integer maxTokens;

    @Builder
    public OpenAIChatRequest(String model, List<Message> messages, Integer maxTokens) {
        this.model = model;
        this.messages = messages;
        this.maxTokens = maxTokens;
    }

    @Getter
    public static class Message {
        private final String role;
        private final String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
