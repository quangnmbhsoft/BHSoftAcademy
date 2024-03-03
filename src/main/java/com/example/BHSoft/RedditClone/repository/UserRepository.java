package com.example.BHSoft.RedditClone.repository;

import com.example.BHSoft.RedditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String email, String password);
    User findByUsername(String email);

    User findFirstByEmail(String email);
}
