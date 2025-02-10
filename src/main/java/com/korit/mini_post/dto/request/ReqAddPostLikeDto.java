package com.korit.mini_post.dto.request;

import com.korit.mini_post.entity.Post;
import com.korit.mini_post.entity.PostLike;
import lombok.Data;

@Data
public class ReqAddPostLikeDto {
    private int postId;
    private int userId;

    public PostLike toPostLike() {
        return PostLike.builder()
                .postId(postId)
                .userId(userId)
                .build();
    }
}
