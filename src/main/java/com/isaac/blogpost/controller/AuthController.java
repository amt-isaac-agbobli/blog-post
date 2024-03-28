package com.isaac.blogpost.controller;

import com.isaac.blogpost.dto.request.SignInRequest;
import com.isaac.blogpost.dto.request.SignUpRequest;
import com.isaac.blogpost.dto.response.SignInResponse;
import com.isaac.blogpost.dto.response.SignUpResponse;
import com.isaac.blogpost.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest user) {
        return ResponseEntity.status(201).body(authService.signUp(user));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest user) {
        return ResponseEntity.ok().body(authService.signIn(user));
    }
}
