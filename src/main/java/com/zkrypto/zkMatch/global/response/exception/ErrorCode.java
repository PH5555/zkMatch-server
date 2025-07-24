package com.zkrypto.zkMatch.global.response.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST("400", HttpStatus.BAD_REQUEST, "입력값이 유효하지 않습니다."),

    NOT_FOUND_MEMBER_ID("A001", HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다."),
    INVALID_MEMBER_PASSWORD("A002", HttpStatus.BAD_REQUEST, "비밀번호가 불일치합니다."),

    NOT_FOUND_MEMBER("M001", HttpStatus.NOT_FOUND, "멤버가 존재하지 않습니다."),
    ID_DUPLICATION("M002", HttpStatus.NOT_FOUND, "중복되는 아이디입니다.");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
}
