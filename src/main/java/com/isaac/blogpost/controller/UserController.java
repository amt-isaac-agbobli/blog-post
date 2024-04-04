package com.isaac.blogpost.controller;

import com.isaac.blogpost.controller.api.UserApi;
import com.isaac.blogpost.dto.request.UpdatePasswordRequest;
import com.isaac.blogpost.dto.response.UserProfileResponse;
import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        UserProfileResponse profile = new UserProfileResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles(),
                user.getIsVerified()
        );

        return ResponseEntity.ok().body(profile);
    }

    @PutMapping("/update-password")
    public ResponseEntity<Void> updatePassword(Authentication authentication,
                                               @Valid @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        User user = (User) authentication.getPrincipal();
        userService.updatePassword(updatePasswordRequest, user.getId());
        return ResponseEntity.noContent().build();
    }
}
