package com.zkrypto.zkMatch.domain.post.application.dto.response;

import lombok.Getter;

@Getter
public class PostApplierResponse {
    private String applierName;
    private Boolean isSatisfied;
    private String applierPersonalStatement;
    private Boolean status;
}
