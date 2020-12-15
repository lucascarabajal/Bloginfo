package com.example.informatorio.blog.bloginfo.repository;

import com.example.informatorio.blog.bloginfo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
