package com.zkrypto.zkMatch.domain.member.domain.repository;

import com.zkrypto.zkMatch.domain.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findMemberByLoginId(String loginId);

    boolean existsByLoginId(String loginId);
}
