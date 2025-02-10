package com.korit.mini_post.mapper;

import com.korit.mini_post.entity.PostLike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostLikeMapper {
    int insert(int postId, int userId);

    int delete(int postId, int userId);

    int getLikeCount(int postId);
}
