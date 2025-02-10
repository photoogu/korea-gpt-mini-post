package com.korit.mini_post.service;

import com.korit.mini_post.dto.request.ReqAddPostDto;
import com.korit.mini_post.entity.Post;
import com.korit.mini_post.entity.PostLike;
import com.korit.mini_post.exception.CustomDuplicateKeyException;
import com.korit.mini_post.repository.PostRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional(rollbackFor = Exception.class)
    public Post addPost(ReqAddPostDto reqAddPostDto) {
        Post savePost = postRepository.addPost(reqAddPostDto.toPost())
                .orElseThrow(() -> new NullPointerException("정보가 없습니다. 글좀 쓰소."));
        return savePost;
    }

    public List<Post> getAllPosts() throws NotFoundException {
        return postRepository.selectAllPosts()
                .orElseThrow(() -> new NotFoundException("게시글 정보가 존재하지 않습니다."));
    }

    public Post getPostById(int postId) throws NotFoundException {
        return postRepository.selectPostById(postId)
                .orElseThrow(() -> new NotFoundException("게시글 정보가 존재하지 않습니다."));

    }

}
