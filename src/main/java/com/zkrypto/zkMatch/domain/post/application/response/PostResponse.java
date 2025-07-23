package com.zkrypto.zkMatch.domain.post.application.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponse {
    private Long postId;
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
}
