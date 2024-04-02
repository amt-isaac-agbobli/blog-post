package com.isaac.blogpost.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreatePostRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Content is required")
        @Length(min = 10, message = "Content must be at least 10 characters long")
        String content
) {
}
