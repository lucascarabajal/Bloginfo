package com.example.informatorio.blog.bloginfo.service;

import com.example.informatorio.blog.bloginfo.Exception.ResourceNotFoundException;
import com.example.informatorio.blog.bloginfo.entity.Post;
import com.example.informatorio.blog.bloginfo.entity.User;
import com.example.informatorio.blog.bloginfo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public ResponseEntity<Post> addPost (Post post){
        Post newPost = postRepository.save(post);
        return ResponseEntity.ok(newPost);
    }

    public ResponseEntity<List<Post>> getPosts(){
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    public ResponseEntity<Post> getPostById(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()){
            return ResponseEntity.ok(optionalPost.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<String> deletePostById(Long id){
        postRepository.deleteById(id);
        String message = "Post: "+id+"ha sido eliminado";
        return ResponseEntity.ok(message);
    }

    public Object updatePost (Post post){
        Optional<Post> optionalPost = postRepository.findById(post.getId());
        if (optionalPost.isPresent()){
            Post postToUpDate = optionalPost.get();
            postToUpDate.setTitle(post.getTitle());
            postToUpDate.setContent(post.getContent());
            postToUpDate.setDescription(post.getDescription());
            postToUpDate.setPublished(post.getPublished());
            postRepository.save(postToUpDate);
            return postToUpDate;
        }else{
            throw new ResourceNotFoundException("Post no encontrado");
        }
    }

    public List<Post> getTitleWithWord(String wordTitle) {
        return postRepository.getTitleWithWord(wordTitle);
    }

    public List<Post> getPostNotPublished() {
        return postRepository.getPostNotPublished();
    }
}
