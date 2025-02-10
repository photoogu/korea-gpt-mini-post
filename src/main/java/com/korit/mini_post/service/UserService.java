package com.korit.mini_post.service;

import com.korit.mini_post.dto.request.ReqAddUserDto;
import com.korit.mini_post.entity.User;
import com.korit.mini_post.exception.CustomDuplicateKeyException;
import com.korit.mini_post.repository.UserRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(int userId) throws NotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 사용자 ID는 존재하지 않습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public User addUser(ReqAddUserDto reqAddUserDto) throws NotFoundException {
        User saveUser = userRepository.save(reqAddUserDto.toUser())
                .orElseThrow(
                        () -> new CustomDuplicateKeyException("", Map.of("username", "이미 사용중인 사용자 이름입니다."))
                );
        return saveUser;
    }
}
