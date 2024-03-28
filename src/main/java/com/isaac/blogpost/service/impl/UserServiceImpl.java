package com.isaac.blogpost.service.impl;

import com.isaac.blogpost.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}
