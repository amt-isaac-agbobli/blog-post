package com.isaac.blogpost.repository;

import com.isaac.blogpost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
