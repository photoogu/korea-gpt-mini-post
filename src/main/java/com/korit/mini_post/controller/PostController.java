package com.korit.mini_post.controller;

import com.korit.mini_post.dto.request.ReqAddPostDto;
import com.korit.mini_post.dto.response.common.SuccessResponseDto;
import com.korit.mini_post.entity.Post;
import com.korit.mini_post.entity.PostLike;
import com.korit.mini_post.repository.PostRepository;
import com.korit.mini_post.service.PostLikeService;
import com.korit.mini_post.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.Response;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@Api(tags = "게시글 정보 API")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostLikeService postLikeService;

    @PostMapping("/api/post")
    public ResponseEntity<SuccessResponseDto<Post>> addPost(@RequestBody ReqAddPostDto reqAddPostDto) {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.addPost(reqAddPostDto)));
    }

    @GetMapping("/api/posts")
    @ApiOperation(value = "게시글 전체 조회")
    public ResponseEntity<SuccessResponseDto<List<Post>>> getAllPosts() throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.getAllPosts()));
    }

    @GetMapping("/api/post/{postId}")
    @ApiOperation(value = "게시글 단건 조회")
    public ResponseEntity<SuccessResponseDto<Post>> getPost(
            @Min(value = 1, message = "게시글 ID 는 1이상의 정수입니다.")
            @ApiParam(value = "게시글 ID", example = "1", required = true)
            @PathVariable int postId
    ) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postService.getPostById(postId)));
    }

    @PostMapping("/api/post/{postId}/like/{userId}")
    @ApiOperation(value = "게시글 좋아요 추가")
    public ResponseEntity<SuccessResponseDto<PostLike>> addPostLike(
            @Min(value = 1, message = "게시글 ID는 1이상의 정수입니다.")
            @ApiParam(value = "게시글 ID", example = "1", required = true)
            @PathVariable int postId,
            @Min(value = 1, message = "사용자 ID는 1이상의 정수입니다.")
            @ApiParam(value = "사용자 ID", example = "1", required = true)
            @PathVariable int userId
    ) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postLikeService.save(postId, userId)));
    }

    @DeleteMapping("/api/post/{postId}/like/{userId}")
    @ApiOperation(value = "게시글 좋아요 삭제")
    public ResponseEntity<SuccessResponseDto<?>> deletePostLike(
            @Min(value = 1, message = "게시글 ID는 1이상의 정수입니다.")
            @ApiParam(value = "게시글 ID", example = "1", required = true)
            @PathVariable int postId,
            @Min(value = 1, message = "사용자 ID는 1이상의 정수입니다.")
            @ApiParam(value = "사용자 ID", example = "1", required = true)
            @PathVariable int userId
    ) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(postLikeService.delete(postId, userId)));
    }

    @GetMapping("/api/post/{postId}/like/count")
    @ApiOperation(value = "게시글 좋아요 개수 조회")
    public ResponseEntity<SuccessResponseDto<?>> getPostLikeCount(
            @Min(value = 1, message = "게시글 ID는 1이상의 정수입니다.")
            @ApiParam(value = "게시글 ID", example = "1", required = true)
            @PathVariable int postId
            ) {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(null));
    }
}
