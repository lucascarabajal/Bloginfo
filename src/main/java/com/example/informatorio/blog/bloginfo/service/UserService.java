package com.example.informatorio.blog.bloginfo.service;

import com.example.informatorio.blog.bloginfo.Exception.ResourceNotFoundException;
import com.example.informatorio.blog.bloginfo.entity.User;
import com.example.informatorio.blog.bloginfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> addUser(User user) {
        User newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }

    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<User> getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<String> deleteUserById(Long id) {
        userRepository.deleteById(id);
        String message = "Usuario: "+id+" eliminado.";
        return ResponseEntity.ok(message);
    }

    public Object updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent()) {
            User userToUpDate = optionalUser.get();
            userToUpDate.setName(user.getName());
            userToUpDate.setLast_name(user.getLast_name());
            userToUpDate.setEmail(user.getEmail());
            userToUpDate.setPassword(user.getPassword());
            userToUpDate.setCountry(user.getCountry());
            userToUpDate.setState(user.getState());
            userToUpDate.setCity(user.getCity());
            userRepository.save(userToUpDate);
            return userToUpDate;
        } else {
            throw new ResourceNotFoundException("Usuario no encontrado con Id "+user.getId());
        }
    }

    public List<User> getUsersForCity(String city) {
        return userRepository.getUsersForCity(city);
    }

    public List<User> getUserFromRegisterDate(LocalDate date){
        return userRepository.getUserForRegisterDate(date);
    }
}
