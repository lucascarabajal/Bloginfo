package com.example.informatorio.blog.bloginfo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate commentCreationDate;

    @Column(nullable = false, length = 200)
    private String comment;

    @ManyToOne
    @JsonBackReference("userComment")
    @JoinColumn(name = "fkUser", nullable = false)
    private User author;

    @ManyToOne
    @JsonBackReference("postComment")
    @JoinColumn(name = "fkPost", nullable = false)
    private Post postComment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCommentCreationDate() {
        return commentCreationDate;
    }

    public void setCommentCreationDate(LocalDate commentCreationDate) {
        this.commentCreationDate = commentCreationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPostComment() {
        return postComment;
    }

    public void setPostComment(Post postComment) {
        this.postComment = postComment;
    }
}
