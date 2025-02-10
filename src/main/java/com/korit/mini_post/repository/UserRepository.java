package com.korit.mini_post.repository;

import com.korit.mini_post.entity.User;
import com.korit.mini_post.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public Optional<User> findById(int userId) {
        return Optional.ofNullable(userMapper.selectById(userId));
    }

    public Optional<User> save (User user) {
        try {
            userMapper.insert(user);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

}
