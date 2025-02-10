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

    public Optional<Post> addPost (Post post) {
        postMapper.insertPost(post);
        return Optional.of(post);
    }

    public Optional<List<Post>> selectAllPosts() {
        List<Post> foundPosts = postMapper.selectAllPost();
        return Optional.of(foundPosts);
    }

    public Optional<Post> selectPostById(int postId) {
        return Optional.of(postMapper.selectPostById(postId));
    }
}
