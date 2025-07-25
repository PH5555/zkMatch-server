package com.zkrypto.zkMatch.domain.member.domain.entity;

import com.zkrypto.zkMatch.domain.auth.application.dto.request.SignUpCommand;
import com.zkrypto.zkMatch.domain.member.domain.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID memberId;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String loginId;
    private String password;
    private String refreshToken;
    private String name;
    private String email;
    private String phoneNumber;

    private Member(Role role, String loginId, String password) {
        this.role = role;
        this.loginId = loginId;
        this.password = password;
    }

    public void signOut() {
        this.refreshToken = null;
    }

    public void storeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static Member from(SignUpCommand signUpCommand, String hashedPassword) {
        return new Member(Role.ROLE_USER, signUpCommand.getLoginId(), hashedPassword);
    }
}
