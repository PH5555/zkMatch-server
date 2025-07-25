package com.zkrypto.zkMatch.domain.member.application.dto.response;

import com.zkrypto.zkMatch.domain.member.domain.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private String name;
    private String email;
    private String phoneNumber;

    private MemberResponse(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getName(), member.getEmail(), member.getPhoneNumber());
    }
}
