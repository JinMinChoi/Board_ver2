package me.jinmin.boardver2.user.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.user.api.request.UserUpdateRequest;
import me.jinmin.boardver2.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserFindService userFindService;

    @Transactional
    public Long update(Long userId, UserUpdateRequest userUpdateRequest) {
        User findUser = userFindService.findById(userId);
        User updatedUser = findUser.updateUserInfo(
                userUpdateRequest.getName(),
                userUpdateRequest.getPassword()
        );
        return updatedUser.getId();
    }
}
