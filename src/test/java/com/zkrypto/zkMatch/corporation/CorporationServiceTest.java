package com.zkrypto.zkMatch.corporation;

import com.zkrypto.zkMatch.domain.corporation.application.dto.request.CorporationCreationCommand;
import com.zkrypto.zkMatch.domain.corporation.application.service.CorporationService;
import com.zkrypto.zkMatch.domain.corporation.domain.repository.CorporationRepository;
import com.zkrypto.zkMatch.domain.member.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CorporationServiceTest {
    @Autowired
    CorporationService corporationService;

    @Autowired
    CorporationRepository corporationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 생성_테스트() {
        // 기업 생성
        CorporationCreationCommand command = new CorporationCreationCommand(null, "1234", "지크립토", null, null, "1234", "1234");
        corporationService.createCorporation(command);

        // member 생성 테스트
        boolean memberTest = memberRepository.existsByLoginId("1234");
        assertThat(memberTest).isTrue();

        // 기업 생성 테스트
        boolean corporationTest = corporationRepository.existsCorporationByRegisterNumber("1234");
        assertThat(corporationTest).isTrue();
    }
}
