package com.isaac.blogpost.jwt;

import com.isaac.blogpost.entity.User;

public interface JwtService {
    public String extractSubject(String token);
    public String generateToken(User user);
}
