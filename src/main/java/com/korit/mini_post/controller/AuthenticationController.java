package com.korit.mini_post.controller;

import com.korit.mini_post.dto.request.ReqSigninDto;
import com.korit.mini_post.dto.request.ReqSignupDto;
import com.korit.mini_post.dto.response.common.SuccessResponseDto;
import com.korit.mini_post.entity.User;
import com.korit.mini_post.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "계정 API")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/auth/signup")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<SuccessResponseDto<User>> signup(@Valid @RequestBody ReqSignupDto reqSignupDto) throws Exception {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(authenticationService.signup(reqSignupDto)));
    }

    @PostMapping("/api/auth/signin")
    @ApiOperation(value = "로그인")
    public ResponseEntity<SuccessResponseDto<String>> signin(@Valid @RequestBody ReqSigninDto reqSigninDto) throws Exception {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(authenticationService.signin(reqSigninDto)));
    }
}
