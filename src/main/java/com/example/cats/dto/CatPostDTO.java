package com.example.cats.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatPostDTO {

    @NotEmpty(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser nulo")
    private String name;
    @NotEmpty(message = "Gênero não pode ser vazio")
    @NotNull(message = "Gênero não pode ser nulo")
    private String gender;
    @NotNull(message = "Idade não pode ser nula")
    private int age;

}
