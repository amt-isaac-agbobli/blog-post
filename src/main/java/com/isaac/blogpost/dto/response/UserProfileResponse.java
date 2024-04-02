package com.isaac.blogpost.dto.response;

import com.isaac.blogpost.enums.Role;

public record UserProfileResponse(
        Long id,
        String name,
        String email,

        Role role,
        Boolean isVerified
) {
}
