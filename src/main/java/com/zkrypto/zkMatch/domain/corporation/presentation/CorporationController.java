package com.zkrypto.zkMatch.domain.corporation.presentation;

import com.zkrypto.zkMatch.domain.corporation.application.dto.request.CorporationCreationCommand;
import com.zkrypto.zkMatch.domain.corporation.application.service.CorporationService;
import com.zkrypto.zkMatch.domain.post.application.response.CorporationPostResponse;
import com.zkrypto.zkMatch.domain.post.application.request.PassApplierCommand;
import com.zkrypto.zkMatch.domain.post.application.request.PostCreationCommand;
import com.zkrypto.zkMatch.domain.post.application.response.PostApplierResponse;
import com.zkrypto.zkMatch.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/corporation")
@RequiredArgsConstructor
@Tag(name = "CorporationController", description = "기업 관련 API")
public class CorporationController {

    private final CorporationService corporationService;

    @Operation(
            summary = "기업 정보 조회 API",
            description = "기업 정보를 조회 합니다.",
            security = {
                    @SecurityRequirement(name = "bearerAuth")
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.HEADER,
                            name = "Authorization",
                            description = "Bearer 토큰(ADMIN)",
                            required = true
                    )
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(schema = @Schema(implementation = Void.class))}),
    })
    @GetMapping()
    public void getCorporation(@AuthenticationPrincipal UUID memberId) {

    }

    @Operation(
            summary = "기업 생성 API",
            description = "기업과 기업 인사 당담자 계정을 생성 합니다."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(schema = @Schema(implementation = Void.class))}),
    })
    @PostMapping()
    public ApiResponse<Void> createCorporation(@RequestBody CorporationCreationCommand corporationCreationCommand) {
        corporationService.createCorporation(corporationCreationCommand);
        return ApiResponse.success();
    }

    @Operation(
            summary = "기업 채용 공고 조회 API",
            description = "내 기업의 채용 공고를 모두 조회 합니다.",
            security = {
                    @SecurityRequirement(name = "bearerAuth")
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.HEADER,
                            name = "Authorization",
                            description = "Bearer 토큰(ADMIN)",
                            required = true
                    )
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CorporationPostResponse.class)))}),
    })
    @GetMapping("/post")
    public ApiResponse<List<CorporationPostResponse>> getPost(@AuthenticationPrincipal UUID memberId){
        return ApiResponse.success(null);
    }

    @Operation(
            summary = "채용 공고 생성 API",
            description = "채용 공고를 생성합니다.",
            security = {
                    @SecurityRequirement(name = "bearerAuth")
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.HEADER,
                            name = "Authorization",
                            description = "Bearer 토큰(ADMIN)",
                            required = true
                    )
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(schema = @Schema(implementation = Void.class))}),
    })
    @PostMapping("/post")
    public void createPost(@RequestBody PostCreationCommand postCreationCommand){

    }

    @Operation(
            summary = "채용 공고 상세 API",
            description = "해당 공고의 지원자들을 불러옵니다.",
            security = {
                    @SecurityRequirement(name = "bearerAuth")
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.HEADER,
                            name = "Authorization",
                            description = "Bearer 토큰(ADMIN)",
                            required = true
                    )
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(array = @ArraySchema(schema = @Schema(implementation = PostApplierResponse.class)))}),
    })
    @GetMapping("/post/{postId}")
    public ApiResponse<List<PostApplierResponse>> getPostApplier(@PathVariable(name = "postId") String postId){
        return ApiResponse.success(null);
    }

    @Operation(
            summary = "합격 통지 API",
            description = "해당 지원자를 합격 처리합니다.",
            security = {
                    @SecurityRequirement(name = "bearerAuth")
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.HEADER,
                            name = "Authorization",
                            description = "Bearer 토큰(ADMIN)",
                            required = true
                    )
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요청 성공",
                    content = {@Content(schema = @Schema(implementation = Void.class))}),
    })
    @PutMapping("/post/{postId}")
    public void passApplier(@PathVariable(name = "postId") String postId, @RequestBody PassApplierCommand passApplierCommand){
    }
}
