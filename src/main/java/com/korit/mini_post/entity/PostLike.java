package com.korit.mini_post.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLike {
    private int postLikeId;
    private int postId;
    private int userId;

    public PostLike(int postId, int userId) {}
}
