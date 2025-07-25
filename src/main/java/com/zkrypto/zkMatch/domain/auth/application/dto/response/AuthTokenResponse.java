package com.zkrypto.zkMatch.domain.auth.application.dto.response;

import com.zkrypto.zkMatch.global.jwt.JwtToken;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AuthTokenResponse {
    private String accessToken;
    private String refreshToken;

    private AuthTokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static AuthTokenResponse from(JwtToken jwtToken) {
        return new AuthTokenResponse(jwtToken.getAccessToken(), jwtToken.getRefreshToken());
    }
}
