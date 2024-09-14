package me.woodgeon.minidelivery.dto.signin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SigninResponse {
    private String loginId;
    private String nickname;
    private String address;
    private String accessToken;
    private boolean success;
    private String message;
}
