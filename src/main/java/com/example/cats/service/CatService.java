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

    public List<Cat> findByName(String name) {
        return catRepository.findByName(name);
    }

    public Cat save(Cat cat) {
        return catRepository.save(cat);
    }

    public void delete(Long id) {
        catRepository.delete(findById(id));
    }

    public void replace(Cat cat){
        catRepository.save(cat);
    }

}
