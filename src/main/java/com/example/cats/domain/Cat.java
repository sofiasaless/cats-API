package com.example.cats.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull(message = "Nome não pode ser vazio ou nulo")
    private String name;
    @NotNull(message = "Gênero não pode ser vazio ou nulo")
    private String gender;
    @NotNull(message = "Idade não pode ser vazia ou nula")
    private int age;


}
