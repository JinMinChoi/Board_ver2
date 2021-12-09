package me.jinmin.boardver2.user.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.user.exception.NotFoundUserException;
import me.jinmin.boardver2.user.model.User;
import me.jinmin.boardver2.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFindService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException(String.format("There is no Id : %s", userId)));
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException(String.format("There is no email : %s, You need to SignUp", email)));
    }
}
