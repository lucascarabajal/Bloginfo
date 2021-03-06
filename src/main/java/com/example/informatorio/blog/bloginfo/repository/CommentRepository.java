package com.example.informatorio.blog.bloginfo.repository;

import com.example.informatorio.blog.bloginfo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value="SELECT * FROM Comment WHERE fk_post = :id_post ORDER BY id DESC LIMIT :lim", nativeQuery = true)
    public List<Comment> getFilterComments(@Param("id_post")Long id_post,@Param("lim") Integer lim);
}
