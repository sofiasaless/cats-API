package com.example.cats.service;

import com.example.cats.dto.CatPostDTO;
import com.example.cats.dto.CatMapper;
import com.example.cats.dto.CatPutDTO;
import com.example.cats.exceptions.BadRequestException;
import com.example.cats.model.Cat;
import com.example.cats.repository.CatRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Cat findByIdOrThrowBadRequestException(Long id) {
        return catRepository.findById(id).orElseThrow(
                () -> new BadRequestException("Cat not found"));
    }

    public List<Cat> findByName(String name) {
        return catRepository.findByName(name);
    }

    public Cat save(CatPostDTO catPostDTO) {
        return catRepository.save(CatMapper.INSTANCE.toCat(catPostDTO));
    }

    public void delete(Long id) {
        catRepository.delete(findById(id));
    }

    public void replace(@NotNull CatPutDTO cat){
        // gato que vai ser atualizado agora
        Cat catUpdated = Cat.builder()
                .id(cat.getId())
                .name(cat.getName())
                .gender(cat.getGender())
                .age(cat.getAge())
        .build();

        catRepository.save(catUpdated);
    }

}
