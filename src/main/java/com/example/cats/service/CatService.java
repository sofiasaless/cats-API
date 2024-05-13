package com.example.cats.service;

import com.example.cats.dto.CatDTO;
import com.example.cats.dto.CatMapper;
import com.example.cats.model.Cat;
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

    public Cat save(CatDTO catDTO) {
        return catRepository.save(CatMapper.INSTANCE.toCat(catDTO));
    }

    public void delete(Long id) {
        catRepository.delete(findById(id));
    }

    public void replace(Cat cat){
        catRepository.save(cat);
    }

}
