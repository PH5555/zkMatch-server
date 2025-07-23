package com.zkrypto.zkMatch.domain.post.application.response;

import lombok.Getter;

@Getter
public class CorporationPostResponse {
    private String postId;
    private String title;
    private int applierCount;
    private int passedCount;
    private String period;
}
