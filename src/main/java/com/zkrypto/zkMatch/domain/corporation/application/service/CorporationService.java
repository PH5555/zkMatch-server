package com.zkrypto.zkMatch.domain.corporation.application.service;

import com.zkrypto.zkMatch.domain.corporation.application.dto.request.CorporationCreationCommand;
import com.zkrypto.zkMatch.domain.corporation.application.dto.response.CorporationResponse;
import com.zkrypto.zkMatch.domain.corporation.domain.entity.Corporation;
import com.zkrypto.zkMatch.domain.corporation.domain.repository.CorporationRepository;
import com.zkrypto.zkMatch.domain.member.domain.entity.Member;
import com.zkrypto.zkMatch.domain.member.domain.repository.MemberRepository;
import com.zkrypto.zkMatch.global.response.exception.CustomException;
import com.zkrypto.zkMatch.global.response.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CorporationService {
    private final CorporationRepository corporationRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
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
    public CorporationResponse getCorporation(UUID memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        return new CorporationResponse(member.getCorporation().getCorporationName());
    }
}
