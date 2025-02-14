package com.korit.mini_post.repository;

import com.korit.mini_post.entity.Post;
import com.korit.mini_post.mapper.PostMapper;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    @Autowired
    private PostMapper postMapper;

    public Optional<Post> save(Post post) {
        int successCount = postMapper.insert(post);
        return successCount < 1 ? Optional.empty() : Optional.of(post);
    }

    public Optional<Post> findById(int id) {
        Post post = postMapper.selectById(id);
        return Optional.ofNullable(post);
    }

    public Optional<List<Post>> findAllByKeywordContaining(int startIndex, int limitCount, String keyword) {
        List<Post> posts = postMapper.selectAllByKeywordContaining(startIndex, limitCount, keyword);
        return posts.isEmpty() ? Optional.empty() : Optional.of(posts);
    }


}
