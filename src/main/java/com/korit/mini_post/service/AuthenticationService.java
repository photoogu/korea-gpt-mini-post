package com.korit.mini_post.service;

import com.korit.mini_post.dto.request.ReqSigninDto;
import com.korit.mini_post.dto.request.ReqSignupDto;
import com.korit.mini_post.entity.User;
import com.korit.mini_post.exception.CustomDuplicateKeyException;
import com.korit.mini_post.repository.UserRepository;
import com.korit.mini_post.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Transactional(rollbackFor = Exception.class)
    public User signup(ReqSignupDto reqSignupDto) {
        User user = reqSignupDto.toUser(bCryptPasswordEncoder);
        User saveUser = userRepository
                .save(user)
                .orElseThrow(() -> new CustomDuplicateKeyException("이미 존재하는 사용자 이름입니다.", Map.of("username", "이미 존재하는 사용자 이름입니다.")));

        return saveUser;
    }

    public String signin(ReqSigninDto reqSigninDto) {
        String accessToken = null;

        User foundUser = userRepository
                .findByUsername(reqSigninDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 다시 확인하세요."));

        if(!bCryptPasswordEncoder.matches(reqSigninDto.getPassword(), foundUser.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 다시 확인하세요.");
        }

        accessToken = jwtProvider.createAccessToken(foundUser);

        return accessToken;
    }

}
