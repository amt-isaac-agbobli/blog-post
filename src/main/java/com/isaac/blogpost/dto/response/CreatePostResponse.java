package com.isaac.blogpost.dto.response;

public record CreatePostResponse(
        Long id,
        String title,
        String content,
        String author
) {
}
