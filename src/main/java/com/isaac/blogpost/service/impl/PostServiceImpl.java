package com.isaac.blogpost.service.impl;

import com.isaac.blogpost.dto.request.CreatePostRequest;
import com.isaac.blogpost.dto.response.CreatePostResponse;
import com.isaac.blogpost.entity.Post;
import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.repository.PostRepository;
import com.isaac.blogpost.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public CreatePostResponse createPost(CreatePostRequest createPostRequest, User user) {

        Post newPost = Post.builder()
                .content(createPostRequest.content())
                .title(createPostRequest.title())
                .user(user)
                .build();
        postRepository.save(newPost);

        return new CreatePostResponse(postRepository.save(newPost).getId(),
                newPost.getTitle(),
                newPost.getContent(),
                newPost.getUser().getName());
    }
}
