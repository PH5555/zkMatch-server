package com.zkrypto.zkMatch.domain.member.application.dto.response;

import com.zkrypto.zkMatch.domain.post.domain.entity.Post;
import com.zkrypto.zkMatch.domain.scrab.domain.entity.Scrab;
import lombok.Getter;

@Getter
public class MemberScrabResponse {
    private String postId;
    private String postString;

    private MemberScrabResponse(String postId, String postString) {
        this.postId = postId;
        this.postString = postString;
    }

    public static MemberScrabResponse from(Scrab scrab) {
        return new MemberScrabResponse(scrab.getPost().getPostId().toString(), scrab.getPost().getTitle());
    }
}
