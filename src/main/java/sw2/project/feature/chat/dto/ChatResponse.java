package sw2.project.feature.chat.dto;

import lombok.Getter;

@Getter
public class ChatResponse {

    private final String reply;

    public ChatResponse(String reply) {
        this.reply = reply;
    }
}
