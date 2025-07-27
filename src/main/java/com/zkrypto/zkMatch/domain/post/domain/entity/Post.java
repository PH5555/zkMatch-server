package com.zkrypto.zkMatch.domain.post.domain.entity;

import com.zkrypto.zkMatch.domain.corporation.domain.entity.Corporation;
import com.zkrypto.zkMatch.domain.post.application.dto.request.PostCreationCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postId;

    private String title;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "corporation_id")
    private Corporation corporation;

    public Post(String title, Corporation corporation) {
        this.title = title;
        this.corporation = corporation;
    }

    public static Post from(PostCreationCommand command, Corporation corporation) {
        return new Post(command.getTitle(), corporation);
    }
}
