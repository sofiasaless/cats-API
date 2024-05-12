package com.example.cats.repository;

import com.example.cats.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {

}
