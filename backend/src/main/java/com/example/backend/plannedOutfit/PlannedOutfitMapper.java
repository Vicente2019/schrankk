package com.example.backend.plannedOutfit;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlannedOutfitMapper {

    PlannedOutfitMapper INSTANCE = Mappers.getMapper(PlannedOutfitMapper.class);

    PlannedOutfitDTO toDTO(PlannedOutfitEntity plannedOutfitEntity);

    PlannedOutfitEntity toEntity(PlannedOutfitDTO plannedOutfitDTO);
}
