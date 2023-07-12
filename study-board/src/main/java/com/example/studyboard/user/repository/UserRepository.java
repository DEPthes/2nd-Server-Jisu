package com.example.studyboard.user.repository;

import com.example.studyboard.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where username = :username")
    User findByUser(String username);

    Optional<User> findByUsername(String username);
}
