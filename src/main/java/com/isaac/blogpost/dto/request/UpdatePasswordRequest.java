package com.isaac.blogpost.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdatePasswordRequest(
        @NotBlank(message = "Password is required")
        @Length(min = 6, message = "Password must be at least 6 characters")
        String password,
        @NotBlank(message = "Old password is required")
        String oldPassword
) {
}
