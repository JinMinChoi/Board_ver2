package me.jinmin.boardver2.user.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jinmin.boardver2.user.model.User;
import me.jinmin.boardver2.user.service.UserFindService;
import me.jinmin.boardver2.util.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserFindApi {

    private final UserFindService userFindService;

    @GetMapping("/{id}")
    public ApiResult<User> findById(@PathVariable("id") Long userId) {
        try {
            return ApiResult.succeed(userFindService.findById(userId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }

    @GetMapping()
    public ApiResult<?> findUsers() {
        try {
            return ApiResult.succeed(userFindService.findAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }
}
