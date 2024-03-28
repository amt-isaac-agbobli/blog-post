package com.isaac.blogpost.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record SignUpRequest(
        @NotBlank(message = "Name is required")
        String name,
        @Email(message = "Email is not valid")
        String email,
        @NotBlank(message = "Password is required")
        @Length(min = 6, message = "Password must be at least 6 characters")
        String password) {
}
