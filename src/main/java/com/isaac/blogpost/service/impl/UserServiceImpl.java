package com.isaac.blogpost.service.impl;

import com.isaac.blogpost.dto.request.UpdatePasswordRequest;
import com.isaac.blogpost.dto.request.UpdateProfileRequest;
import com.isaac.blogpost.dto.response.UserProfileResponse;
import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.exception.HttpException;
import com.isaac.blogpost.repository.UserRepository;
import com.isaac.blogpost.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
   private  final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;


   @Override
   public void updatePassword(UpdatePasswordRequest updatePasswordRequest, Long userId) {
         Optional<User> user = userRepository.findById(userId);
         if (user.isEmpty()) {
              throw new HttpException("User not found", 404);
         }
         User user1 = user.get();
         if(!passwordEncoder.matches(updatePasswordRequest.oldPassword(), user1.getPassword())) {
              throw new HttpException("Old password is incorrect", 400);
         }
         userRepository.save(user1);
   }
}
