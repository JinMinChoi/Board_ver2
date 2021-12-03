package me.jinmin.boardver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BoardVer2Application {

    public static void main(String[] args) {
        SpringApplication.run(BoardVer2Application.class, args);
    }

}
