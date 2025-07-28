package com.zkrypto.zkMatch.domain.scrab.application.dto.response;

import com.zkrypto.zkMatch.domain.scrab.domain.entity.Scrab;
import lombok.Getter;

@Getter
public class ScrabResponse {
    private String postId;
    private String postTitle;

    private ScrabResponse(String postId, String postTitle) {
        this.postId = postId;
        this.postTitle = postTitle;
    }

    public static ScrabResponse from(Scrab scrab) {
        return new ScrabResponse(scrab.getPost().getPostId().toString(), scrab.getPost().getTitle());
    }
}
