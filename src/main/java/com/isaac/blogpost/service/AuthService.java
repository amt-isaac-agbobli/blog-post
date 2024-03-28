package com.isaac.blogpost.service;

import com.isaac.blogpost.dto.request.SignInRequest;
import com.isaac.blogpost.dto.request.SignUpRequest;
import com.isaac.blogpost.dto.response.SignInResponse;
import com.isaac.blogpost.dto.response.SignUpResponse;

public interface AuthService {
    SignUpResponse signUp(SignUpRequest user);

    SignInResponse signIn(SignInRequest user);
}
