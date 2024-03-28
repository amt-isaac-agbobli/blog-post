package com.isaac.blogpost.service;

import com.isaac.blogpost.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserService {
    Optional<User> findByEmail(String email);
}
