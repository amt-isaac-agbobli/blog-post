package com.isaac.blogpost.controller.api;

import com.isaac.blogpost.dto.request.SignInRequest;
import com.isaac.blogpost.dto.request.SignUpRequest;
import com.isaac.blogpost.dto.response.SignInResponse;
import com.isaac.blogpost.dto.response.SignUpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;
@Tag(name = "Auth Endpoints", description = """
    The `Auth API` is a crucial part of our application that handles all authentication-related operations. It provides endpoints for user registration and login, which are essential for maintaining user sessions and securing user data.

    The `signUp` endpoint allows new users to register for an account. It accepts a `SignUpRequest` object, which includes the user's email, name, and password. Upon successful registration, it returns a `SignUpResponse` object, which includes the user's details and a confirmation that the registration was successful.

    The `signIn` endpoint allows existing users to log in to their accounts. It accepts a `SignInRequest` object, which includes the user's email and password. Upon successful login, it returns a `SignInResponse` object, which includes the user's details and a JWT token for authenticating subsequent requests.

    Both endpoints return appropriate HTTP status codes and error messages in case of any issues, such as invalid request data or unauthorized access. This makes it easy for clients of the API to handle different scenarios and provide appropriate feedback to the users.

    This API is built using Spring Boot and adheres to RESTful principles, making it easy to use and integrate with various front-end technologies. It also uses JWT for authentication, which is a widely accepted standard for securing REST APIs.
    """)
public interface AuthApi {
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User signed up successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SignUpResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @Operation(summary = "Sign up a new user", description = "Sign up a new user with email, name and password")
    ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest user);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User signed in successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SignInResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @Operation(summary = "Sign in a user", description = "Sign in a user with email and password")
    ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest user);
}
