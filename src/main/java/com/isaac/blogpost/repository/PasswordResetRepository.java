package com.isaac.blogpost.repository;

import com.isaac.blogpost.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {
}
