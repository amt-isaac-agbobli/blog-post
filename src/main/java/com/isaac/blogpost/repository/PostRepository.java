package com.isaac.blogpost.repository;

import com.isaac.blogpost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
