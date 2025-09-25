package com.afperdomo2.pizzaya.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.afperdomo2.pizzaya.persistence.domain.dto.CreatePizzaDto;
import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;

@Mapper(componentModel = "spring")
public interface PizzaMapper {

    @Mapping(target = "id", ignore = true) // El ID se genera automáticamente en la base de datos
    PizzaEntity toEntity(CreatePizzaDto dto);
}
