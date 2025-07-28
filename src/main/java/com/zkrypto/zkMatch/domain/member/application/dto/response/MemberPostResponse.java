package com.zkrypto.zkMatch.domain.member.application.dto.response;

import com.zkrypto.zkMatch.domain.recruit.domain.entity.Recruit;
import lombok.Getter;

@Getter
public class MemberPostResponse {
    private String title;

    private MemberPostResponse(String title) {
        this.title = title;
    }

    public static MemberPostResponse from(Recruit recruit) {
        return new MemberPostResponse(recruit.getPost().getTitle());
    }
}
