package com.zkrypto.zkMatch.domain.member.application.service;

import com.zkrypto.zkMatch.domain.member.application.dto.response.MemberPostResponse;
import com.zkrypto.zkMatch.domain.member.application.dto.response.MemberResponse;
import com.zkrypto.zkMatch.domain.member.domain.entity.Member;
import com.zkrypto.zkMatch.domain.member.domain.repository.MemberRepository;
import com.zkrypto.zkMatch.domain.recruit.domain.entity.Recruit;
import com.zkrypto.zkMatch.domain.recruit.domain.repository.RecruitRepository;
import com.zkrypto.zkMatch.global.response.exception.CustomException;
import com.zkrypto.zkMatch.global.response.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RecruitRepository recruitRepository;

    /**
     * 멤버 조회 메서드
     */
    public MemberResponse getMember(UUID memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        return MemberResponse.from(member);
    }

    /**
     * 로그아웃 메서드
     */
    @Transactional
    public void signOut(UUID memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        member.signOut();
    }

    /**
     * 지원 내역 조회 메서드
     */
    public List<MemberPostResponse> getPost(UUID memberId) {
        // 멤버 존재 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        // 지원 내역 조회
        List<Recruit> recruit = recruitRepository.findByMemberWithPost(member);

        return recruit.stream().map(MemberPostResponse::from).toList();
    }
}
