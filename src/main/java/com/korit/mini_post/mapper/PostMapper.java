package com.korit.mini_post.mapper;

import com.korit.mini_post.entity.Post;
import com.korit.mini_post.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    int insert(Post post);
    Post selectById(int postId);
    List<Post> selectAllByKeywordContaining(
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount,
            @Param("keyword") String keyword
    );

    // 좋아요
//    int getLikeCount(int postId);
}
