package com.zkrypto.zkMatch.domain.scrab.domain.entity;

import com.zkrypto.zkMatch.domain.member.domain.entity.Member;
import com.zkrypto.zkMatch.domain.post.domain.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Scrab {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrabId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Scrab(Member member, Post post) {
        this.member = member;
        this.post = post;
    }
}
