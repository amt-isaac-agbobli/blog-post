package com.isaac.blogpost.repository;

import com.isaac.blogpost.entity.EmailVerificationToken;
import com.isaac.blogpost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepository extends JpaRepository<EmailVerificationToken, Long> {
}
