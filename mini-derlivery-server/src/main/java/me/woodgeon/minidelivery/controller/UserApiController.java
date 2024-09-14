package me.woodgeon.minidelivery.controller;

import lombok.RequiredArgsConstructor;
import me.woodgeon.minidelivery.auth.JwtTokenUtil;
import me.woodgeon.minidelivery.dto.update.UpdateRequest;
import me.woodgeon.minidelivery.dto.update.UpdateResponse;
import me.woodgeon.minidelivery.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/update")
    public UpdateResponse updateUser(@RequestBody UpdateRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        // JWT 토큰에서 "Bearer " 접두어 제거
        String actualToken = token.substring(7);

        // 토큰에서 loginId 추출
        String loginId = JwtTokenUtil.getLoginId(actualToken, "yourSecretKey");

        // loginId를 기반으로 사용자 업데이트 처리
        boolean isUpdated = userService.updateUser(loginId, request);

        if (isUpdated) {
            return new UpdateResponse(true, "사용자 정보가 성공적으로 업데이트되었습니다.");
        } else {
            return new UpdateResponse(false, "업데이트에 실패했습니다.");
        }
    }
}
