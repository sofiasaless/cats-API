package com.example.cats.service;

import com.example.cats.dto.CatPostDTO;
import com.example.cats.dto.CatMapper;
import com.example.cats.dto.CatPutDTO;
import com.example.cats.exceptions.BadRequestException;
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

    public void replace(CatPutDTO cat){
        // com esse replace, é preciso preencher todos os atributos cat, mesmo que não sofram atualização, pois o save requere o notEmpty e notNull
        // pensar numa maneira que você possa atualizar somente o campo que deseja, sem precisar preencher todos os atributos do json
        // talvez criar um metodo de update para cada atributo ??? ai espefica no controller o que vai ser atualizado
        Cat catSaved = findByIdOrThrowBadRequestException(cat.getId());
        Cat updatedCat = CatMapper.INSTANCE.toCat(cat);
        updatedCat.setId(catSaved.getId());
        catRepository.save(updatedCat);
    }

}
