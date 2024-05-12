package com.example.cats.service;

import com.example.cats.domain.Cat;
import com.example.cats.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatRepository catRepository;

    public List<Cat> listAll() {
        return catRepository.findAll();
    }

    public Cat findById(Long id) {
        return catRepository.findById(id).orElse(null);
    }
}
