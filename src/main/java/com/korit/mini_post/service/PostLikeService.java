package com.korit.mini_post.service;

import com.korit.mini_post.dto.request.ReqAddPostLikeDto;
import com.korit.mini_post.entity.PostLike;
import com.korit.mini_post.repository.PostLikeRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {
    @Autowired
    private PostLikeRepository postLikeRepository;

    public PostLike save(int postId, int userId) throws NotFoundException {
        PostLike savePostLike = postLikeRepository.save(postId, userId)
                .orElseThrow(() -> new NotFoundException("게시글이 존재하지 않습니다."));
        return savePostLike;
    }

    public Boolean delete(int postId, int userId) throws NotFoundException {
        return postLikeRepository.deletePostLike(postId, userId)
                .orElseThrow(() -> new NotFoundException("게시글이 존재하지 않아용"));
    }

    public Boolean getPostLikeCount(int postId) throws NotFoundException {
        return postLikeRepository.getPostLikeCount(postId)
                .orElseThrow(() -> new NotFoundException("게시글이 존재하지 않습니다."));
    }
}
