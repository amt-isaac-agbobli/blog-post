package com.isaac.blogpost.service;

import com.isaac.blogpost.dto.request.CreatePostRequest;
import com.isaac.blogpost.dto.response.CreatePostResponse;
import com.isaac.blogpost.entity.User;

import java.util.List;

public interface PostService {
    CreatePostResponse createPost(CreatePostRequest createPostRequest, User user);

    List<CreatePostResponse> getAllPosts();
}
