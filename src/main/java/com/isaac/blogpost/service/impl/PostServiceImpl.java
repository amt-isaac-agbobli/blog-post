package com.isaac.blogpost.service.impl;

import com.isaac.blogpost.dto.request.CreatePostRequest;
import com.isaac.blogpost.dto.response.PostResponse;
import com.isaac.blogpost.entity.Post;
import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.repository.PostRepository;
import com.isaac.blogpost.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostResponse createPost(CreatePostRequest createPostRequest, User user) {

        Post newPost = Post.builder()
                .content(createPostRequest.content())
                .title(createPostRequest.title())
                .user(user)
                .build();
        postRepository.save(newPost);

        return new PostResponse(postRepository.save(newPost).getId(),
                newPost.getTitle(),
                newPost.getContent(),
                newPost.getUser().getName());
    }

    @Override
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return mapToResponse(posts);
    }

    @Override
    public List<PostResponse> getPostsByUser(User user) {
        List<Post> posts = postRepository.findByUser(user);
        return mapToResponse(posts);
    }

    @Override
    public List<PostResponse> getPostsByTitle(String title) {
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(title);
        return mapToResponse(posts);
    }

    private   List<PostResponse> mapToResponse(List<Post> posts) {
        return posts.stream().map(post -> new PostResponse(post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getName())).collect(Collectors.toList());
    }
}
