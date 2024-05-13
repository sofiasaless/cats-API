package com.example.cats.dto;

import com.example.cats.model.Cat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CatMapper {
    // metodo para chamar os metodos
    public static final CatMapper INSTANCE = Mappers.getMapper(CatMapper.class);

    // vai transformar o catDTO automaticamente em um objeto cat
    public abstract Cat toCat(CatPostDTO data);
    public abstract Cat toCat(CatPutDTO data);

}
