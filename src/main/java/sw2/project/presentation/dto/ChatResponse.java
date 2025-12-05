package sw2.project.presentation.dto;

import lombok.Getter;

@Getter
public class ChatResponse {

    private final String reply;

    public ChatResponse(String reply) {
        this.reply = reply;
    }
}
