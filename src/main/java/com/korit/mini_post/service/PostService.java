package com.korit.mini_post.service;

import com.korit.mini_post.dto.request.ReqAddPostDto;
import com.korit.mini_post.entity.Post;
import com.korit.mini_post.entity.PostLike;
import com.korit.mini_post.exception.CustomDuplicateKeyException;
import com.korit.mini_post.repository.PostLikeRepository;
import com.korit.mini_post.repository.PostRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Transactional(rollbackFor = Exception.class)
    public Post createPost(ReqAddPostDto reqAddPostDto) {
        return postRepository.save(reqAddPostDto.toPost()).get();
    }

    public Post getPostById(int postId) throws Exception {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("해당 postID의 게시글이 존재하지 않습니다."));
        return post;
    }

    public List<Post> getAllPostsByKeywordContaining(int page, int size, String keyword) throws Exception {
        int startIndex = (page - 1) * size;
        List<Post> posts = postRepository.findAllByKeywordContaining(startIndex, size, keyword)
                .orElseThrow(() -> new NotFoundException("검색된 정보가 없습니다."));
        return posts;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean likePost(int postId, int userId) throws Exception {
        PostLike postLike = PostLike.builder()
                .postId(postId)
                .userId(userId)
                .build();
        return postLikeRepository.save(postLike)
                .orElseThrow(() -> new CustomDuplicateKeyException("", Map.of("message", "해당 게시글을 이미 like 처리하였습니다.")));
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean unlikePost(int postId, int userId) throws Exception {
        PostLike postLike = PostLike.builder()
                .postId(postId)
                .userId(userId)
                .build();
        return postLikeRepository.delete(postLike)
                .orElseThrow(() -> new NotFoundException("해당 postId 또는 userId 로 조회된 좋아요가 없습니다."));
    }
}
