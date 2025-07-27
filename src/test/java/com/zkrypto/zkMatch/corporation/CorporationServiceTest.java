package com.zkrypto.zkMatch.corporation;

import com.zkrypto.zkMatch.domain.corporation.application.dto.request.CorporationCreationCommand;
import com.zkrypto.zkMatch.domain.corporation.application.dto.response.CorporationResponse;
import com.zkrypto.zkMatch.domain.corporation.application.service.CorporationService;
import com.zkrypto.zkMatch.domain.corporation.domain.repository.CorporationRepository;
import com.zkrypto.zkMatch.domain.member.domain.entity.Member;
import com.zkrypto.zkMatch.domain.member.domain.repository.MemberRepository;
import com.zkrypto.zkMatch.domain.post.application.dto.request.PostCreationCommand;
import com.zkrypto.zkMatch.domain.post.domain.entity.Post;
import com.zkrypto.zkMatch.domain.post.domain.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Rollback
@Transactional
public class CorporationServiceTest {
    @Autowired
    CorporationService corporationService;

    @Autowired
    CorporationRepository corporationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostRepository postRepository;

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

    @Test
    void 조회_테스트() {
        // 기업 생성
        CorporationCreationCommand command = new CorporationCreationCommand(null, "1234", "지크립토", null, null, "1234", "1234");
        corporationService.createCorporation(command);

        // 기업 조회
        Member member = memberRepository.findMemberByLoginId("1234").get();
        CorporationResponse corporation = corporationService.getCorporation(member.getMemberId());

        // 조회 테스트
        assertThat(corporation.getCorporationName()).isEqualTo("지크립토");
    }

    @Test
    void 공고_생성_테스트() {
        // 기업 생성
        CorporationCreationCommand command = new CorporationCreationCommand(null, "1234", "지크립토", null, null, "1234", "1234");
        corporationService.createCorporation(command);

        // 멤버 조회
        Member member = memberRepository.findMemberByLoginId("1234").get();

        // 공고 생성
        corporationService.createPost(member.getMemberId(), new PostCreationCommand());

        // 생성 테스트
        List<Post> posts = postRepository.findPostByCorporation(member.getCorporation());
        assertThat(posts.size()).isEqualTo(1);
    }
}
