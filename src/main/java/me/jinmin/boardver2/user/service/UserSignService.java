package me.jinmin.boardver2.user.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.user.api.request.UserLogInRequest;
import me.jinmin.boardver2.user.api.request.UserSignUpRequest;
import me.jinmin.boardver2.user.exception.DuplicatedEmailException;
import me.jinmin.boardver2.user.exception.UnMatchedPasswordException;
import me.jinmin.boardver2.user.model.User;
import me.jinmin.boardver2.user.model.UserRole;
import me.jinmin.boardver2.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignService {

    private final UserRepository userRepository;
    private final UserFindService userFindService;

    @Transactional
    public Long signUp(UserSignUpRequest userSignUpRequest) {
        checkDuplicatedEmail(userSignUpRequest.getEmail());
        User user = User.builder()
                .email(userSignUpRequest.getEmail())
                .password(userSignUpRequest.getPassword())
                .name(userSignUpRequest.getName())
                .userRole(UserRole.NORMAL)
                .build();
        User savedUser = userRepository.save(user);
        return savedUser.getUser_id();
    }

    @Transactional
    public Long logIn(UserLogInRequest userLogInRequest) {
        User user = userFindService.findByEmail(userLogInRequest.getEmail());
        checkMatchedPassword(userLogInRequest.getPassword(), user.getPassword());
        return user.getUser_id();
    }

    private void checkDuplicatedEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicatedEmailException(String.format("\"%s\" already exist.", email));
        }
    }

    private void checkMatchedPassword(String loginPassword, String userPassword) {
        if (!loginPassword.equals(userPassword)) {
            throw new UnMatchedPasswordException(String.format("Password is not matched"));
        }
    }
}
