package me.woodgeon.minidelivery.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.woodgeon.minidelivery.domain.User;
import me.woodgeon.minidelivery.dto.signin.SigninRequest;
import me.woodgeon.minidelivery.dto.signup.SignupRequest;
import me.woodgeon.minidelivery.dto.update.UpdateRequest;
import me.woodgeon.minidelivery.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean checkLoginIdDuplicate(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public void signup(SignupRequest req) {
        userRepository.save(req.toEntity());
    }

    public User login(SigninRequest req) {
        Optional<User> optionalUser = userRepository.findByLoginId(req.getLoginId());

        if(optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        if(!user.getPassword().equals(req.getPassword())) {
            return null;
        }

        return user;
    }

    public User getLoginUserById(Long userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    public User getLoginUserByLoginId(String loginId) {
        if(loginId == null) return null;

        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();

    }
    public boolean updateUser(String userId, UpdateRequest request) {
        Optional<User> userOptional = userRepository.findByLoginId(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNickname(request.getNickname());
            user.setAddress(request.getAddress());
            userRepository.save(user); // 업데이트된 정보 저장
            return true;
        }

        return false; // 사용자를 찾지 못한 경우
    }


}
