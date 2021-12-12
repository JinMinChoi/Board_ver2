package me.jinmin.boardver2.user.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserRepository userRepository;

    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
