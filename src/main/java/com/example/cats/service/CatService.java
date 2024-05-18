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
        // nesse novo update, o json poderá ser preenchido apenas com a informação que o usuario desejar atualizar
        // exemplo: quero atualizar apenas o nome do gato cadastrado ... vai precisar só do id e do novo nome
        // antes precisaria preencher gender e age para que fosse possível atualizar o gato
        // obs: não sei se vai ser realmente útil e usável na api qnd tiver trabalhando no front-end

        // pegando as informações do gato que vai ser atualzado
        Cat catSaved = findByIdOrThrowBadRequestException(cat.getId());

        // definido os atributos que vão ser atualizados a partir das informações que chegaram
        String nome = Optional.ofNullable(cat.getName()).isPresent()?cat.getName():catSaved.getName();
        String genero = Optional.ofNullable(cat.getGender()).isPresent()?cat.getGender():catSaved.getGender();
        boolean ageIsNullOrZero = Optional.of(cat.getAge()).map(i -> i == 0).orElse(true);
        int idade = ageIsNullOrZero?catSaved.getAge():cat.getAge();

        // gato que vai ser atualizado agora
        Cat catUpdated = Cat.builder()
                .id(cat.getId())
                .name(nome)
                .gender(genero)
                .age(idade)
        .build();

        catRepository.save(catUpdated);
    }

}
