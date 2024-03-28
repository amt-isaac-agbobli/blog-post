package com.isaac.blogpost.dto.response;

public record UserProfileResponse(
        Long id,
        String name,
        String email,
        Boolean isVerified
) {
}
