package me.jinmin.boardver2.user.api;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.user.service.UserDeleteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserDeleteApi {

    private final UserDeleteService userDeleteService;

    @DeleteMapping("/delete/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userDeleteService.deleteUserById(userId);
    }
}
