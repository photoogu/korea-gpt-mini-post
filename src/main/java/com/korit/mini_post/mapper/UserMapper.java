package com.korit.mini_post.mapper;

import com.korit.mini_post.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectById(int userId);
    int insert(User user);
    User selectByUsername(String username);
}
