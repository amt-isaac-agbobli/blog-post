package com.isaac.blogpost.repository;

import com.isaac.blogpost.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Long> {
}
