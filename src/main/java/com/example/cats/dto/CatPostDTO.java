package com.example.cats.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Nome para cadastro do gato", example = "Garfield")
    private String name;
    @NotEmpty(message = "Gênero não pode ser vazio")
    @NotNull(message = "Gênero não pode ser nulo")
    @Schema(description = "Gênero para cadastro do gato", example = "Fêmea")
    private String gender;
    @NotNull(message = "Idade não pode ser nula")
    @Schema(description = "Idade para cadastro do gato")
    private int age;

}
