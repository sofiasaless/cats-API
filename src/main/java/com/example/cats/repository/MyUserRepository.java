package com.example.cats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cats.model.MyUser;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
}
