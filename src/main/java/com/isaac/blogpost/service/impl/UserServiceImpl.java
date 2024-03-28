package com.isaac.blogpost.service.impl;

import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.repository.UserRepository;
import com.isaac.blogpost.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
   private  final UserRepository userRepository;



}
