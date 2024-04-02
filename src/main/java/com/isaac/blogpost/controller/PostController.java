package com.isaac.blogpost.controller;

import com.isaac.blogpost.dto.request.CreatePostRequest;
import com.isaac.blogpost.dto.response.CreatePostResponse;
import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<CreatePostResponse> createPost(@Valid @RequestBody CreatePostRequest createPostRequest,
                                                         Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(postService.createPost(createPostRequest, user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CreatePostResponse>> getAllPosts() {
        return ResponseEntity.ok().body(postService.getAllPosts());
    }
}
