package com.zkrypto.zkMatch.domain.corporation.application.service;

import com.zkrypto.zkMatch.domain.corporation.application.dto.request.CorporationCreationCommand;
import com.zkrypto.zkMatch.domain.corporation.application.dto.response.CorporationResponse;
import com.zkrypto.zkMatch.domain.corporation.domain.entity.Corporation;
import com.zkrypto.zkMatch.domain.corporation.domain.repository.CorporationRepository;
import com.zkrypto.zkMatch.domain.member.domain.entity.Member;
import com.zkrypto.zkMatch.domain.member.domain.repository.MemberRepository;
import com.zkrypto.zkMatch.domain.post.application.dto.request.PostCreationCommand;
import com.zkrypto.zkMatch.domain.post.application.dto.response.CorporationPostResponse;
import com.zkrypto.zkMatch.domain.post.domain.entity.Post;
import com.zkrypto.zkMatch.domain.post.domain.repository.PostRepository;
import com.zkrypto.zkMatch.global.response.exception.CustomException;
import com.zkrypto.zkMatch.global.response.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CorporationService {
    private final CorporationRepository corporationRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final PostRepository postRepository;

    /**
     * 기업 생성 메서드
     */
    @Transactional
    public void createCorporation(CorporationCreationCommand corporationCreationCommand) {
        // 기업 중복 확인
        if(corporationRepository.existsCorporationByRegisterNumber(corporationCreationCommand.getCorporationRegisterNumber())) {
            throw new CustomException(ErrorCode.REGISTER_NUMBER_DUPLICATION);
        }

        // 인사담당자 아이디 중복 확인
        if(memberRepository.existsByLoginId(corporationCreationCommand.getLoginId())){
            throw new CustomException(ErrorCode.ID_DUPLICATION);
        }

        // 인사담당자 생성
        String hashedPassword = passwordEncoder.encode(corporationCreationCommand.getPassword());
        Member member = Member.from(corporationCreationCommand.getLoginId(), hashedPassword);

        // 기업 생성
        Corporation corporation = Corporation.from(corporationCreationCommand, member);
        corporationRepository.save(corporation);
    }

    /**
     * 기업 조회 메서드
     */
    @Transactional
    public CorporationResponse getCorporation(UUID memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        return new CorporationResponse(member.getCorporation().getCorporationName());
    }

    /**
     * 기업 공고 생성 메서드
     */
    public void createPost(UUID memberId, PostCreationCommand postCreationCommand) {
        // 멤버 존재 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        // 공고 생성
        Post post = Post.from(postCreationCommand, member.getCorporation());
        postRepository.save(post);
    }

    /**
     * 기업 공고 조회 메서드
     */
    public List<CorporationPostResponse> getCorporationPost(UUID memberId) {
        // 멤버 존재 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        List<Post> posts = postRepository.findPostByCorporation(member.getCorporation());
        return posts.stream().map(this::toCorporationPostResponse).toList();
    }

    private CorporationPostResponse toCorporationPostResponse(Post post) {
        return CorporationPostResponse.from(post, 1,2);
    }
}
