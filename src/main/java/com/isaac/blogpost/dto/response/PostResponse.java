package com.isaac.blogpost.dto.response;

public record PostResponse(
        Long id,
        String title,
        String content,
        String author
) {
}
