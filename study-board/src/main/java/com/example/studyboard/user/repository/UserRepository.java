package com.example.studyboard.user.repository;

import com.example.studyboard.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where user_id = :user_id")
    User findByUser(String userId);

    Optional<User> findByUserId(String userId);

}
