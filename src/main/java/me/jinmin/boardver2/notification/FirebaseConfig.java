package me.jinmin.boardver2.notification;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;

//1. AccessToken 발급 받기

@Configuration
public class FirebaseConfig {

    @Bean
    public GoogleCredentials getGoogleCredentials() throws IOException {
        return GoogleCredentials
                .fromStream(new ClassPathResource("firebase/jinminboard-firebase-adminsdk-9ataf-770210e8eb.json").getInputStream())
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
    }

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(getGoogleCredentials())
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
