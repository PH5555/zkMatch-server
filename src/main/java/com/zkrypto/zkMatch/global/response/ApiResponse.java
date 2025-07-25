package com.zkrypto.zkMatch.global.response;

import com.zkrypto.zkMatch.global.response.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    private ApiHeader header;
    private T data;

    private static final int SUCCESS = 200;
    private static final int INTERNAL_ERROR = 500;

    private ApiResponse(ApiHeader header, T data) {
        this.header = header;
        this.data = data;
    }

    private ApiResponse(ApiHeader header) {
        this.header = header;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(new ApiHeader(SUCCESS, "SUCCESS"), data);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<T>(new ApiHeader(SUCCESS, "SUCCESS"), null);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<T>(new ApiHeader(errorCode.getHttpStatus().value(), errorCode.getMessage()), null);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<T>(new ApiHeader(INTERNAL_ERROR, message), null);
    }
}
