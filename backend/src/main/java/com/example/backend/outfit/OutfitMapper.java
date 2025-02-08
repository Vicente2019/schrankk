package com.example.backend.outfit;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OutfitMapper {

    OutfitMapper INSTANCE = Mappers.getMapper(OutfitMapper.class);

    OutfitDTO map(OutfitEntity outfitEntity);

    OutfitEntity map(OutfitDTO outfitDTO);
}
