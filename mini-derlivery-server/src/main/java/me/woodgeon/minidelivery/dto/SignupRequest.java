package me.woodgeon.minidelivery.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.woodgeon.minidelivery.domain.User;
import me.woodgeon.minidelivery.domain.UserRole;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {
    private String loginId;
    private String password;
    private String passwordCheck;
    private String nickname;

    // 비밀번호 암호화 X
    public User toEntity() {
        return User.builder()
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .role(UserRole.USER)
                .build();
    }

    // 비밀번호 암호화
    public User toEntity(String encodedPassword) {
        return User.builder()
                .loginId(this.loginId)
                .password(encodedPassword)
                .nickname(this.nickname)
                .role(UserRole.USER)
                .build();
    }
}
