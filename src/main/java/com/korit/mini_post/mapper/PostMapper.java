package com.korit.mini_post.mapper;

import com.korit.mini_post.entity.Post;
import com.korit.mini_post.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    int insertPost(Post post);
    List<Post> selectAllPost();
    Post selectPostById(int postId);

    // 좋아요
//    int getLikeCount(int postId);
}
