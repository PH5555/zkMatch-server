package com.zkrypto.zkMatch.domain.auth.presentation;

import com.zkrypto.zkMatch.domain.auth.application.dto.request.ReissueCommand;
import com.zkrypto.zkMatch.domain.auth.application.dto.request.SignInCommand;
import com.zkrypto.zkMatch.domain.auth.application.dto.request.SignUpCommand;
import com.zkrypto.zkMatch.domain.auth.application.dto.response.AuthTokenResponse;
import com.zkrypto.zkMatch.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "AuthController", description = "로그인 및 회원가입 API")
public class AuthController {
    @PostMapping("/signup")
    @Operation(summary = "회원가입 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(schema = @Schema(implementation = Void.class))}),
    })
    public void signUp(@RequestBody SignUpCommand signUpCommand) {
    }

    @Operation(summary = "로그인 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(schema = @Schema(implementation = AuthTokenResponse.class))}),
    })
    @PostMapping("/signin")
    public ApiResponse<AuthTokenResponse> signIn(@RequestBody SignInCommand signInCommand) {
        return ApiResponse.success(null);
    }

    @Operation(summary = "토큰 재발급 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(schema = @Schema(implementation = AuthTokenResponse.class))}),
    })
    @PostMapping("/reissue")
    public ApiResponse<AuthTokenResponse> reissue(@RequestBody ReissueCommand reissueCommand) {
        return ApiResponse.success(null);
    }
}
