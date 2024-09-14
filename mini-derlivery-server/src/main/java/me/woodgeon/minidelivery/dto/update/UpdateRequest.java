package me.woodgeon.minidelivery.dto.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.woodgeon.minidelivery.domain.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {
    private String nickname;
    private String address;

    public User toEntity() {
        return User.builder()
                .nickname(this.nickname)
                .address(this.address)
                .build();
    }
}
