package com.zkrypto.zkMatch.domain.recruit.domain.repository;

import com.zkrypto.zkMatch.domain.member.domain.entity.Member;
import com.zkrypto.zkMatch.domain.post.domain.entity.Post;
import com.zkrypto.zkMatch.domain.recruit.domain.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    boolean existsByMemberAndPost(Member member, Post post);

    List<Recruit> findByMember(Member member);

    List<Recruit> findByPost(Post post);

    @Query("select recruit from Recruit recruit left join fetch recruit.member where recruit.post = :post")
    List<Recruit> findByPostWithMember(@Param("post") Post post);
}
