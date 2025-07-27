package com.zkrypto.zkMatch.domain.post.application.dto.response;

import com.zkrypto.zkMatch.domain.post.domain.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponse {
    private String postId;
    private String title;
    private String corporationName;
    private String corporationAddress;
    private String jobType;
    private String jobHistory;
    private String period;
    private String schoolHistory;
    private List<String> licenses;
    private int salary;
    private int recruitCount;

    public PostResponse(String postId, String title, String corporationName) {
        this.postId = postId;
        this.title = title;
        this.corporationName = corporationName;
    }

    public static PostResponse from(Post post) {
        return new PostResponse(post.getPostId().toString(), post.getTitle(), post.getCorporation().getCorporationName());
    }
}
