package com.isaac.blogpost.dto.response;

public record SignUpResponse(
        Long id,
        String name,
        String email,
        Boolean isVerified
) {
}
