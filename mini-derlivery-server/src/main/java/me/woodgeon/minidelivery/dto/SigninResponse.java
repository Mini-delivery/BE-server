package me.woodgeon.minidelivery.dto;

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
    private String accessToken;
    private boolean success;
    private String message;
}
