package com.zkrypto.zkMatch.domain.auth.application.dto.request;

import lombok.Getter;

@Getter
public class SignUpCommand {
    private String loginId;
    private String password;
    private String email;
    private String name;
    private String phoneNumber;
}
