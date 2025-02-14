package com.korit.mini_post.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    private int userRoleId;
    private int userId;
    private int roleId;

    private Role role;
}
