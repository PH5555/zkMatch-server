package com.zkrypto.zkMatch.global.jwt;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtToken {
    private String accessToken;
    private String refreshToken;
}