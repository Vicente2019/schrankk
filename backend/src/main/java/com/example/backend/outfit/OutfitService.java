package com.example.backend.outfit;

import com.example.backend.item.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutfitService {

    private final OutfitRepository repository;
    private final OutfitMapper mapper;

    @Autowired
    public OutfitService(OutfitRepository repository, OutfitMapper outfitMapper) {
        this.repository = repository;
        this.mapper = outfitMapper;
    }

    public List<OutfitDTO> getAllOutfits() {
        List<OutfitEntity> entities = repository.findAll();
        return entities.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public OutfitDTO getOutfitById(String id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    public OutfitDTO createOutfit(OutfitDTO outfitDTO) {
        OutfitEntity outfitEntity = mapper.toEntity(outfitDTO);
        outfitEntity = repository.save(outfitEntity);
        return mapper.toDTO(outfitEntity);
    }

    public void deleteOutfit(String id) {
        repository.deleteById(id);
    }

    public OutfitDTO updateOutfit(String id, OutfitDTO outfitDTO) {
        return repository.findById(id)
                .map(existingEntity -> {
                    existingEntity.setName(outfitDTO.getName());
                    existingEntity.setDescription(outfitDTO.getDescription());
                    existingEntity.setItemIds(outfitDTO.getItemIds());
                    OutfitEntity updatedEntity = repository.save(existingEntity);
                    return mapper.toDTO(updatedEntity);
                })
                .orElse(null);
    }
}
