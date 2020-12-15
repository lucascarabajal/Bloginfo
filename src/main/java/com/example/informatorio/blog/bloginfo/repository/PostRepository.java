package com.example.informatorio.blog.bloginfo.repository;

import com.example.informatorio.blog.bloginfo.entity.Post;
import com.example.informatorio.blog.bloginfo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Query(value = "SELECT * FROM Post AS p WHERE p.title LIKE %:wordTitle%", nativeQuery = true)
    public List<Post> getTitleWithWord(@Param("wordTitle") String wordTitle);

    @Query("SELECT p FROM Post p WHERE p.published = 0")
    public List<Post> getPostNotPublished();
}
