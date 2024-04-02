package com.isaac.blogpost.service;

import com.isaac.blogpost.dto.request.CreatePostRequest;
import com.isaac.blogpost.dto.response.PostResponse;
import com.isaac.blogpost.entity.User;

import java.util.List;

public interface PostService {
    PostResponse createPost(CreatePostRequest createPostRequest, User user);

    List<PostResponse> getAllPosts();

    List<PostResponse> getPostsByUser(User user);

    List<PostResponse> getPostsByTitle(String title);

    PostResponse updatePost(Long id, CreatePostRequest createPostRequest, User user);
}
