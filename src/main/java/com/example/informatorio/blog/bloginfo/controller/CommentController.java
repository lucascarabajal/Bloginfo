package com.example.informatorio.blog.bloginfo.controller;

import com.example.informatorio.blog.bloginfo.entity.Comment;
import com.example.informatorio.blog.bloginfo.entity.Post;
import com.example.informatorio.blog.bloginfo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/comment")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComment(){
        return ResponseEntity.ok().body(commentService.getComment());
    }

    @RequestMapping("{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id")Long id){
        return commentService.getCommentById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateComment(@PathVariable Long id, @RequestBody Comment comment){
        comment.setId(id);
        return ResponseEntity.ok().body(this.commentService.updateComment(comment));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") Long id){
        return commentService.deleteCommentById(id);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Comment>> getFilterComments(@RequestParam(value="id_post")Long id_post,@RequestParam(value = "lim",required = false) Integer lim) {
        if (lim == null){
            lim = commentService.getComment().size();
        }
        return ResponseEntity.ok().body(commentService.getFilterComments(id_post,lim));
    }
}
