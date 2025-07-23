package com.zkrypto.zkMatch.domain.post.application.request;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostCreationCommand {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String schoolHistory;
    private String jobHistory;
    private List<String> licenses;
    private int salary;
    private String location;
    private String jobType;
    private int recruitCount;
}
