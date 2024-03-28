package com.isaac.blogpost.dto.request;

public record SignInRequest(
        String email,
        String password
) {
}
