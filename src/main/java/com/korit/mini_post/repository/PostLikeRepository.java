package com.korit.mini_post.repository;

import com.korit.mini_post.entity.PostLike;
import com.korit.mini_post.mapper.PostLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostLikeRepository {

    @Autowired
    private PostLikeMapper postLikeMapper;

    public Optional<PostLike> save(int postId, int userId) {
        PostLike postLike = new PostLike(postId, userId);
        return postLikeMapper.insert(postId, userId) < 1 ? Optional.empty() : Optional.of(postLike);
    }

    public Optional<Boolean> deletePostLike(int postId, int userId) {
        return postLikeMapper.delete(postId, userId) < 1 ? Optional.empty() : Optional.of(true);
    }

    public Optional<Boolean> getPostLikeCount(int postId) {
        return postLikeMapper.getLikeCount(postId) < 1 ? Optional.empty() : Optional.of(true);
    }
}
