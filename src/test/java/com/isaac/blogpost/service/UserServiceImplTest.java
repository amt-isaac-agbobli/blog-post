package com.isaac.blogpost.service;

import com.isaac.blogpost.dto.request.UpdatePasswordRequest;
import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.exception.HttpException;
import com.isaac.blogpost.repository.UserRepository;
import com.isaac.blogpost.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
 class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UpdatePasswordRequest updatePasswordRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setId(1L);
        user.setPassword("oldPassword");

        updatePasswordRequest = new UpdatePasswordRequest("oldPassword", "newPassword");

    }

    @Test
    void updatePasswordSuccessfully() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        userService.updatePassword(updatePasswordRequest, 1L);
    }

    @Test
    void updatePasswordThrowsExceptionWhenUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(HttpException.class, () -> userService.updatePassword(updatePasswordRequest, 1L));
    }

    @Test
    void updatePasswordThrowsExceptionWhenOldPasswordIncorrect() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        assertThrows(HttpException.class, () -> userService.updatePassword(updatePasswordRequest, 1L));
    }
}