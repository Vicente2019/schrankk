package com.example.backend.item;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemDTO toDTO(ItemEntity itemEntity);

    ItemEntity toEntity(ItemDTO itemDTO);
}
