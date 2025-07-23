package com.zkrypto.zkMatch.global.response;

import com.zkrypto.zkMatch.global.response.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    private ApiHeader header;
    private T data;

    private static final int SUCCESS = 200;

    private ApiResponse(ApiHeader header, T data) {
        this.header = header;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(new ApiHeader(SUCCESS, "SUCCESS"), data);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<T>(new ApiHeader(errorCode.getHttpStatus().value(), errorCode.getMessage()), null);
    }
}
