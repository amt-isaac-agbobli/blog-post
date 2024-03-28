package com.isaac.blogpost.dto.response;

public record SignInResponse(
        Long id,
        String name,
        String email,
        Boolean isVerified,
        String accessToken
) {
}
