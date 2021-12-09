package me.jinmin.boardver2.user.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jinmin.boardver2.user.api.request.UserLogInRequest;
import me.jinmin.boardver2.user.api.request.UserSignUpRequest;
import me.jinmin.boardver2.user.service.UserSignService;
import me.jinmin.boardver2.util.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserSignApi {

    private final UserSignService userSignService;

    @PostMapping("/signup")
    public ApiResult<Long> signUp(@Valid @RequestBody UserSignUpRequest userSignUpRequest) {
        try {
            Long userId = userSignService.signUp(userSignUpRequest);
            return ApiResult.succeed(userId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ApiResult<Long> login(@Valid @RequestBody UserLogInRequest userLogInRequest) {
        try {
            Long userId = userSignService.logIn(userLogInRequest);
            return ApiResult.succeed(userId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }
}
