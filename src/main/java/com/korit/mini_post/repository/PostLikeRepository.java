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

    public Optional<Boolean> save(PostLike postLike) {
        return postLikeMapper.insert(postLike) < 1 ? Optional.empty() : Optional.of(true);
    }

    public Optional<Boolean> delete(PostLike postLike) {
        return postLikeMapper.deleteByPostIdAndUserId(postLike) < 1 ? Optional.empty() : Optional.of(true);
    }
}
