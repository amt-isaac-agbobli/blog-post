package com.isaac.blogpost.controller;

import com.isaac.blogpost.controller.api.PostApi;
import com.isaac.blogpost.dto.request.CreatePostRequest;
import com.isaac.blogpost.dto.response.PostResponse;
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
public class PostController implements PostApi {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest createPostRequest,
                                                   Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(postService.createPost(createPostRequest, user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    @GetMapping("/user")
    public ResponseEntity<List<PostResponse>> getPostsByUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(postService.getPostsByUser(user));
    }

    @GetMapping("/title")
    public ResponseEntity<List<PostResponse>> getPostsByTitle(@RequestParam String title) {
        return ResponseEntity.ok().body(postService.getPostsByTitle(title));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id,
                                                   @Valid @RequestBody CreatePostRequest createPostRequest,
                                                   Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(postService.updatePost(id, createPostRequest, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@Valid @PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        postService.deletePost(id, user);
        return ResponseEntity.noContent().build();
    }
}
