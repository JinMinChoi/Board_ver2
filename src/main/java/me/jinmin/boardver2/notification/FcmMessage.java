package me.jinmin.boardver2.notification;

import com.google.api.client.util.Key;
import com.google.firebase.messaging.Message;
import lombok.Builder;
import lombok.Getter;

//2. Request Body 알림 요청 메시지 만들기

@Getter
public class FcmMessage {

    @Key("validate_only")
    private boolean validateOnly;

    // Message 에 해당하는 데이터는 많지만,
    // 사용할 데이터는 notification(Notification), token(String) 뿐
    // Notification => title(String), body(String)
    private Message message;

    @Builder
    public FcmMessage(boolean validateOnly, Message message) {
        this.validateOnly = validateOnly;
        this.message = message;
    }
}
