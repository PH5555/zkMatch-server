package com.zkrypto.zkMatch.domain.post.application.dto.response;

import com.zkrypto.zkMatch.domain.post.domain.entity.Post;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class CorporationPostResponse {
    private String postId;
    private String title;
    private int applierCount;
    private int passedCount;
    private String period;

    private CorporationPostResponse(String postId, String title, int applierCount, int passedCount, String period) {
        this.postId = postId;
        this.title = title;
        this.applierCount = applierCount;
        this.passedCount = passedCount;
        this.period = period;
    }

    public static CorporationPostResponse from(Post post, int applierCount, int passedCount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String startDate = post.getStartDate().format(formatter);
        String endDate = post.getEndDate().format(formatter);
        String period = startDate + " ~ " + endDate;
        return new CorporationPostResponse(post.getPostId().toString(), post.getTitle(), applierCount, passedCount, period);
    }
}
