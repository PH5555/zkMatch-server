package com.zkrypto.zkMatch.domain.post.domain.repository;

import com.zkrypto.zkMatch.domain.corporation.domain.entity.Corporation;
import com.zkrypto.zkMatch.domain.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findPostByCorporation(Corporation corporation);
}
