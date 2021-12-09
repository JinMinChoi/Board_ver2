package me.jinmin.boardver2.user.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLogInRequest {
    private String email;
    private String password;
}
