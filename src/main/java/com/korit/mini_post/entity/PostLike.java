package com.korit.mini_post.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLike {
    private int postLikeId;
    private int postId;
    private int userId;
    private LocalDateTime createdAt;

    private int likeCount;
}
