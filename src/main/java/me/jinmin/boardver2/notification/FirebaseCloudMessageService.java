package me.jinmin.boardver2.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;

//3. FCM에 PUSH 알림 요청을 위한 http post request 만들기

@Slf4j
@Service
@RequiredArgsConstructor
public class FirebaseCloudMessageService {

    private static final String API_URI = "https://fcm.googleapis.com/v1/projects/jinminboard/messages:send";

    private final GoogleCredentials googleCredentials;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    /**
     * `targetToken`에 해당하는 기기로 푸시 알림 전송 요청
     * (targetToken 은 프론트 사이드에서 얻기!)
     */
    public void sendMessageTo(String targetToken, String title, String body) throws FirebaseMessagingException, IOException {

        //Request + Respons 사용
        RequestBody requestBody = getRequestBody(makeFcmMessage(targetToken, title, body));
        Request request = getRequest(requestBody);
        Response response = getResponse(request);
        log.info(response.body().string());

        //FirebaseMessaging 사용
        /*Message message = makeMessage(targetToken, title, body);
        String response = FirebaseMessaging.getInstance().send(message);
        log.info(response);*/
    }

    private Response getResponse(Request request) throws IOException {
        return client.newCall(request).execute();
    }

    private Request getRequest(RequestBody requestBody) {
        return new Request.Builder()
                .url(API_URI)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + googleCredentials.getAccessToken().getTokenValue())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();
    }

    private RequestBody getRequestBody(String message) {
        return RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
    }

    private String makeFcmMessage(String targetToken, String title, String body) throws JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(Message.builder()
                        .setToken(targetToken)
                        .setNotification(new Notification(title, body))
                        .build())
                .validateOnly(false)
                .build();

        log.info("message 변환(Object -> String) \n" + objectMapper.writeValueAsString(fcmMessage));
        return objectMapper.writeValueAsString(fcmMessage);
    }

    private Message makeMessage(String targetToken, String title, String body) {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(Message.builder()
                        .setToken(targetToken)
                        .setNotification(new Notification(title, body))
                        .build())
                .validateOnly(false)
                .build();

        return fcmMessage.getMessage();
    }
}
