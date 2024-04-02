package com.isaac.blogpost.service;

import com.isaac.blogpost.dto.request.UpdatePasswordRequest;
import com.isaac.blogpost.dto.request.UpdateProfileRequest;
import com.isaac.blogpost.dto.response.UserProfileResponse;

public interface UserService {
      void updatePassword(UpdatePasswordRequest updatePasswordRequest, Long userId);

}
