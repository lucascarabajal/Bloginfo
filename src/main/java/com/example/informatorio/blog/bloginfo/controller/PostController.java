package com.example.informatorio.blog.bloginfo.controller;

import com.example.informatorio.blog.bloginfo.entity.Post;
import com.example.informatorio.blog.bloginfo.entity.User;
import com.example.informatorio.blog.bloginfo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/post")
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(){
        return postService.getPosts();
    }

    @RequestMapping("{id}")
    public ResponseEntity<Object> updatePost(@PathVariable Long id, @RequestBody Post post){
        post.setId(id);
        return ResponseEntity.ok().body(this.postService.updatePost(post));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long id){
        return postService.deletePostById(id);
    }

    @RequestMapping("/title/{wordTitle}")
    public ResponseEntity<List<Post>> getTitleWithWord(@PathVariable("wordTitle") String wordTitle) {
        return ResponseEntity.ok().body(postService.getTitleWithWord(wordTitle));
    }

    @GetMapping("/published")
    public ResponseEntity<List<Post>> getPostNotPublished(){
        return ResponseEntity.ok().body(postService.getPostNotPublished());
    }
}
