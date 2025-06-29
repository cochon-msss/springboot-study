package me.sonminseong.springbootblogdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.sonminseong.springbootblogdeveloper.dto.CreateAccessTokenRequest;
import me.sonminseong.springbootblogdeveloper.dto.CreateAccessTokenResponse;
import me.sonminseong.springbootblogdeveloper.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request){
        // 해당 토큰이 데이터베이스에 있다면 새로운 액세스토큰을 생성해서 return해준다.
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }
}
