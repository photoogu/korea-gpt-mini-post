package com.korit.mini_post.controller;

import com.korit.mini_post.dto.request.ReqAddUserDto;
import com.korit.mini_post.dto.response.common.SuccessResponseDto;
import com.korit.mini_post.entity.User;
import com.korit.mini_post.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Api(tags = "사용자 정보 API")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user/{userId}")
    @ApiOperation(value = "사용자 ID로 조회")
    public ResponseEntity<SuccessResponseDto<User>> getUser(
            @Min(value = 1, message = "사용자 ID는 1이상의 정수입니다.")
            @ApiParam(value = "사용자 ID", example = "1", required = true)
            @PathVariable int userId) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.getUserById(userId)));
    }

    @PostMapping("api/user")
    @ApiOperation(value = "사용자 추가")
    public ResponseEntity<SuccessResponseDto<User>> addUser(
            @Valid
            @RequestBody
            ReqAddUserDto reqAddUserDto
    ) throws NotFoundException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.addUser(reqAddUserDto)));
    }
}
