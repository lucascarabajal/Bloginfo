package com.example.informatorio.blog.bloginfo.repository;

import com.example.informatorio.blog.bloginfo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.city = :city")
    public List<User> getUsersForCity(@Param("city") String city);

    @Query("SELECT u FROM User u WHERE u.registerDate > :date")
    public List<User> getUserForRegisterDate(@Param("date")LocalDate date);
}
