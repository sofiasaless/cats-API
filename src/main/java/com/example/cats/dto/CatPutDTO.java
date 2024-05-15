package com.example.cats.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatPutDTO {
    @NotNull(message = "Para atualizar gato, ID não pode ser vazio ou nulo")
    private Long id;
    private String name;
    private String gender;
    private int age;

}
