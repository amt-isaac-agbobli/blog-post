package com.isaac.blogpost.controller.api;

import com.isaac.blogpost.dto.request.UpdatePasswordRequest;
import com.isaac.blogpost.dto.response.UserProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

@Tag(name = "User Endpoints ", description = """
    The `User API` is a part of the application that manages user-related operations. It provides two main endpoints: one for retrieving the profile of the currently authenticated user and another for updating the user's password.

    The `getUserProfile` endpoint returns the profile of the currently authenticated user. It returns a `UserProfileResponse` object which includes the user's details.

    The `updatePassword` endpoint allows the authenticated user to update their password. It accepts an `UpdatePasswordRequest` object which includes the old and new passwords.

    Both endpoints return appropriate HTTP status codes and error messages in case of any issues, such as unauthorized access or user not found. This makes it easy for clients of the API to handle different scenarios and provide appropriate feedback to the users.

    This API is built using Spring Boot and adheres to RESTful principles, making it easy to use and integrate with various front-end technologies. It also uses JWT for authentication, which is a widely accepted standard for securing REST APIs.
    """)
@SecurityRequirement(name = "bearer-key")
public interface UserApi {

    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User profile retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @Operation(summary = "Get user profile", description = "Get the profile of the currently authenticated user")
    ResponseEntity<UserProfileResponse> getUserProfile(Authentication authentication);

    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Password updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @Operation(summary = "Update user password", description = "Update the password of the currently authenticated user")
    ResponseEntity<Void> updatePassword(Authentication authentication,
                                        @Valid @RequestBody UpdatePasswordRequest updatePasswordRequest);
}