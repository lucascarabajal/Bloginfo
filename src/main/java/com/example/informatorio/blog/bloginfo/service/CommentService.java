package com.example.informatorio.blog.bloginfo.service;

import com.example.informatorio.blog.bloginfo.Exception.ResourceNotFoundException;
import com.example.informatorio.blog.bloginfo.entity.Comment;
import com.example.informatorio.blog.bloginfo.entity.Post;
import com.example.informatorio.blog.bloginfo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepositoy;

    public ResponseEntity<Comment> addComment (Comment comment){
        Comment newComment = commentRepositoy.save(comment);
        return ResponseEntity.ok(newComment);
    }

    public ResponseEntity<List<Comment>> getComment(){
        List<Comment> comments = commentRepositoy.findAll();
        return ResponseEntity.ok(comments);
    }

    public ResponseEntity<Comment> getCommentById(Long id){
        Optional<Comment> commentOptional = commentRepositoy.findById(id);
        if(commentOptional.isPresent()){
            return ResponseEntity.ok(commentOptional.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<String> deleteCommentById(Long id){
        commentRepositoy.deleteById(id);
        String message = "Comment: "+id+"ha sido eliminado";
        return ResponseEntity.ok(message);
    }

    public Object updateComment(Comment comment){
        Optional<Comment> optionalComment = commentRepositoy.findById(comment.getId());
        if (optionalComment.isPresent()){
            Comment commentToUpDate = optionalComment.get();
            commentToUpDate.setComment(comment.getComment());
            commentRepositoy.save(commentToUpDate);
            return commentToUpDate;
        }else{
            throw new ResourceNotFoundException("Comentario no encotrado");
        }
    }

    public List<Comment> filterComments(Long lim) {
        return commentRepositoy.filterComments(lim);
    }




}
