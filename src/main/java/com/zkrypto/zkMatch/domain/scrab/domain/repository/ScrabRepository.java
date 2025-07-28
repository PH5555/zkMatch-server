package com.zkrypto.zkMatch.domain.scrab.domain.repository;

import com.zkrypto.zkMatch.domain.member.domain.entity.Member;
import com.zkrypto.zkMatch.domain.scrab.domain.entity.Scrab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScrabRepository extends JpaRepository<Scrab, Long> {
    @Query("select scrab from Scrab scrab inner join fetch scrab.post where scrab.member = :member")
    List<Scrab> findByMember(@Param("member") Member member);
}
