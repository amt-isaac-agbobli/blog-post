package com.isaac.blogpost.controller.api;

import com.isaac.blogpost.dto.request.CreatePostRequest;
import com.isaac.blogpost.dto.response.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Post Endpoint", description = """
    The `PostApi` interface in your application defines several endpoints related to blog post operations:

    1. `createPost`: This endpoint allows the creation of a new post. It accepts a `CreatePostRequest` object which includes the details of the post to be created, and the `Authentication` object of the currently authenticated user. It returns a `PostResponse` object which includes the details of the created post.

    2. `getAllPosts`: This endpoint retrieves all the posts in the system. It does not require any input parameters and returns a list of `PostResponse` objects.

    3. `getPostsByUser`: This endpoint retrieves all the posts created by the currently authenticated user. It requires the `Authentication` object of the currently authenticated user and returns a list of `PostResponse` objects.

    4. `getPostsByTitle`: This endpoint retrieves all the posts with a specific title. It requires a `title` parameter and returns a list of `PostResponse` objects.

    5. `updatePost`: This endpoint allows the updating of an existing post. It accepts a `CreatePostRequest` object which includes the new details of the post, the `id` of the post to be updated, and the `Authentication` object of the currently authenticated user. It returns a `PostResponse` object which includes the details of the updated post.

    6. `deletePost`: This endpoint allows the deletion of an existing post. It requires the `id` of the post to be deleted and the `Authentication` object of the currently authenticated user. It does not return any content.

    All these endpoints return appropriate HTTP status codes and error messages in case of any issues, such as unauthorized access or post not found. This makes it easy for clients of the API to handle different scenarios and provide appropriate feedback to the users.

    This API is built using Spring Boot and adheres to RESTful principles, making it easy to use and integrate with various front-end technologies. It also uses JWT for authentication, which is a widely accepted standard for securing REST APIs.
    """)
@SecurityRequirement(name = "bearer-key")
public interface PostApi {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Operation(summary = "Create a new post", description = "Create a new post")
    ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest createPostRequest,
                                            Authentication authentication);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Operation(summary = "Get all posts", description = "Get all posts")
    ResponseEntity<List<PostResponse>> getAllPosts();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Operation(summary = "Get posts by user", description = "Get posts created by the currently authenticated user")
    ResponseEntity<List<PostResponse>> getPostsByUser(Authentication authentication);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Operation(summary = "Get posts by title", description = "Get posts by title")
    ResponseEntity<List<PostResponse>> getPostsByTitle(@RequestParam String title);

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Post updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @Operation(summary = "Update post", description = "Update an existing post")
    ResponseEntity<PostResponse> updatePost(@PathVariable Long id,
                                            @Valid @RequestBody CreatePostRequest createPostRequest,
                                            Authentication authentication);
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @Operation(summary = "Delete post", description = "Delete an existing post")
    ResponseEntity<Void> deletePost(@Valid @PathVariable Long id, Authentication authentication);
}
