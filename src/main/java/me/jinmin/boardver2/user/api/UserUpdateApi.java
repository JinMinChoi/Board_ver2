package me.jinmin.boardver2.user.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jinmin.boardver2.user.api.request.UserUpdateRequest;
import me.jinmin.boardver2.user.service.UserUpdateService;
import me.jinmin.boardver2.util.ApiResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserUpdateApi {

    private final UserUpdateService userUpdateService;

    @PutMapping("/{userId}")
    public ApiResult<Long> updateUserInfo(@PathVariable Long userId,
                                       @RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            return ApiResult.succeed(userUpdateService.update(userId, userUpdateRequest));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }
}
