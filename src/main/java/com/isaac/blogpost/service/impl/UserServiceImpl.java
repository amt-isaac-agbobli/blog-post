package com.isaac.blogpost.service.impl;

import com.isaac.blogpost.entity.User;

import java.util.Optional;

public interface UserServiceImpl {
    Optional<User> findByEmail(String email);
}
