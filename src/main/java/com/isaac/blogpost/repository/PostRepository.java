package com.isaac.blogpost.repository;

import com.isaac.blogpost.entity.Post;
import com.isaac.blogpost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);

    List<Post> findByTitleContainingIgnoreCase(String title);
}
