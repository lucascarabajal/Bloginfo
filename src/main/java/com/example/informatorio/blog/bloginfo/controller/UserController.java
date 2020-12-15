package com.example.informatorio.blog.bloginfo.controller;

import com.example.informatorio.blog.bloginfo.entity.User;
import com.example.informatorio.blog.bloginfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return userService.getUsers();
    }

    @RequestMapping("{id}")
    public ResponseEntity<User> getUserByd (@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return ResponseEntity.ok().body(this.userService.updateUser(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }
}
