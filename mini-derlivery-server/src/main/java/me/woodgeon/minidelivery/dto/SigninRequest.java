package me.woodgeon.minidelivery.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SigninRequest {
    private String loginId;
    private String password;

}
