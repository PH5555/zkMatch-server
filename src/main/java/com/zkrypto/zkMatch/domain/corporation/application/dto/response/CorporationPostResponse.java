package com.zkrypto.zkMatch.domain.corporation.application.dto.response;

import lombok.Getter;

@Getter
public class CorporationPostResponse {
    private String title;
    private int applierCount;
    private int passedCount;
    private String period;
}
