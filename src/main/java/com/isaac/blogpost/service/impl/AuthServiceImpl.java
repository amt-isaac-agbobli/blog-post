package com.isaac.blogpost.service.impl;

import com.isaac.blogpost.dto.request.SignInRequest;
import com.isaac.blogpost.dto.request.SignUpRequest;
import com.isaac.blogpost.dto.response.SignInResponse;
import com.isaac.blogpost.dto.response.SignUpResponse;
import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.enums.Role;
import com.isaac.blogpost.exception.HttpException;
import com.isaac.blogpost.jwt.JwtService;
import com.isaac.blogpost.repository.UserRepository;
import com.isaac.blogpost.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public SignUpResponse signUp(SignUpRequest user) {

        Optional<User> existingUser = userRepository.findByEmail(user.email());
        if (existingUser.isPresent()) {
            throw new HttpException("User with email " + user.email() + " already exists", 409);
        }


        User newUser = User.builder()
                .email(user.email())
                .name(user.name())
                .isVerified(false)
                .roles(Role.ROLE_USER)
                .password(passwordEncoder.encode(user.password()))
                .build();
        userRepository.save(newUser);

        return new SignUpResponse(newUser.getId(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getRoles(),
                newUser.getIsVerified());
    }

    @Override
    public SignInResponse signIn(SignInRequest user) {

        User userExit = userRepository.findByEmail(user.email())
                .orElseThrow(() -> new HttpException("User with email  not found", 404));

        if (!passwordEncoder.matches(user.password(), userExit.getPassword())) {
            throw new HttpException("Invalid credential ", 401);
        }

        String token = jwtService.generateToken(userExit);


        return new SignInResponse(userExit.getId(),
                userExit.getName(),
                userExit.getEmail(),
                userExit.getRoles(),
                userExit.getIsVerified(),
                token);
    }
}
