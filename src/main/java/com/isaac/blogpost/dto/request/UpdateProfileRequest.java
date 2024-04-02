package com.isaac.blogpost.dto.request;

public record UpdateProfileRequest(
        String name,
        String email
) {
}
