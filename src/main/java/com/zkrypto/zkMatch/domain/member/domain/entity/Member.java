package com.zkrypto.zkMatch.domain.member.domain.entity;

import com.zkrypto.zkMatch.domain.member.domain.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID memberId;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String loginId;
    private String password;
    private String refreshToken;

    public void storeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
