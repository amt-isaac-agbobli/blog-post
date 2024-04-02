package com.isaac.blogpost.service;

import com.isaac.blogpost.dto.request.CreatePostRequest;
import com.isaac.blogpost.dto.response.CreatePostResponse;
import com.isaac.blogpost.entity.User;

public interface PostService {
    CreatePostResponse createPost(CreatePostRequest createPostRequest, User user);
}
