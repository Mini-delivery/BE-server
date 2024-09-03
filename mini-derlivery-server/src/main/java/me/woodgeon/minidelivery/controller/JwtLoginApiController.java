package me.woodgeon.minidelivery.controller;

import lombok.RequiredArgsConstructor;
import me.woodgeon.minidelivery.auth.JwtTokenUtil;
import me.woodgeon.minidelivery.domain.User;
import me.woodgeon.minidelivery.dto.SigninRequest;
import me.woodgeon.minidelivery.dto.SignupRequest;
import me.woodgeon.minidelivery.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jwt-login")
public class JwtLoginApiController {
    private final UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody SignupRequest signupRequest) {
        if(userService.checkLoginIdDuplicate(signupRequest.getLoginId())) {
            return "로그인 아이디가 중복됩니다.";
        }
        if(userService.checkNicknameDuplicate(signupRequest.getNickname())) {
            return "닉네임이 중복됩니다.";
        }
        if(!signupRequest.getPassword().equals(signupRequest.getPasswordCheck())) {
            return"바밀번호가 일치하지 않습니다.";
        }

        userService.signup(signupRequest);
        return "회원가입 성공";
    }
    @PostMapping("/login")
    public String login(@RequestBody SigninRequest signinRequest) {
        User user = userService.login(signinRequest);

        if(user == null) {
            return "아이디 혹은 비밀번호가 틀렸습니다.";
        }

        String secretKey = "my-secret-key-123123";
        long expireTimeMs = 1000 * 60 * 60;     // Token 유효 시간 = 60분

        String jwtToken = JwtTokenUtil.createToken(user.getLoginId(), secretKey, expireTimeMs);

        return jwtToken;
    }
    @GetMapping("/info")
    public String userInfo(Authentication auth) {
        User loginUser = userService.getLoginUserByLoginId(auth.name());

        return String.format("loginId : %s\nnickname : %s\nrole : %s",
                loginUser.getLoginId(), loginUser.getNickname(), loginUser.getRole().name());
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "관리자 페이지 접근 성공";
    }
}
