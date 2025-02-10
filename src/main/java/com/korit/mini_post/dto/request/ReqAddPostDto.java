package com.korit.mini_post.dto.request;

import com.korit.mini_post.entity.Post;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqAddPostDto {
    private int userId;
    private String title;
    private String content;

    public Post toPost() {
        return Post.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}
