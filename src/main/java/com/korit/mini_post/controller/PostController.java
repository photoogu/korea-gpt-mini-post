package com.korit.mini_post.controller;

import com.korit.mini_post.dto.request.ReqAddPostDto;
import com.korit.mini_post.dto.response.common.SuccessResponseDto;
import com.korit.mini_post.entity.Post;
import com.korit.mini_post.entity.PostLike;
import com.korit.mini_post.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@Validated
@RestController
@Api(tags = "게시글 정보 API")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<SuccessResponseDto<Post>> addPost(@RequestBody ReqAddPostDto reqAddPostDto) {
        return ResponseEntity.created(URI.create("")).body(new SuccessResponseDto<>(postService.createPost(reqAddPostDto)));
    }

    @GetMapping("/api/post/{postId}")
    @ApiOperation(value = "게시글 단건 조회")
    public ResponseEntity<SuccessResponseDto<Post>> getPost(
            @Min(value = 1, message = "게시글 ID 는 1이상의 정수입니다.")
            @ApiParam(value = "게시글 ID", example = "1", required = true)
            @PathVariable int postId
    ) throws Exception {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.getPostById(postId)));
    }

    @GetMapping("/api/posts")
    @ApiOperation(value = "게시글 다건 조회")
    public ResponseEntity<SuccessResponseDto<List<Post>>> getPosts(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int size,
            @RequestParam(required = false, defaultValue = "") String keyword
    ) throws Exception {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.getAllPostsByKeywordContaining(page, size, keyword)));
    }

    @PostMapping("/api/post/{postId}/like/{userId}")
    @ApiOperation(value = "게시글 좋아요 추가")
    public ResponseEntity<SuccessResponseDto<Boolean>> likePost(
            @PathVariable int postId,
            @PathVariable int userId
    ) throws Exception {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.likePost(postId, userId)));
    }

    @DeleteMapping("/api/post/{postId}/like/{userId}")
    @ApiOperation(value = "게시글 좋아요 삭제")
    public ResponseEntity<SuccessResponseDto<Boolean>> unlikePost(
            @PathVariable int postId,
            @PathVariable int userId
    ) throws Exception {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.unlikePost(postId, userId)));
    }

    @GetMapping("/api/post/{postId}/like/count")
    @ApiOperation(value = "게시글 좋아요 개수 조회")
    public ResponseEntity<SuccessResponseDto<Integer>> getLikeCount() {
        return ResponseEntity.ok().body(null);
    }
}
