package com.example.backend.outfit;

import com.example.backend.exception.ItemNotFoundException;
import com.example.backend.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutfitService {

    private final OutfitRepository outfitRepository;
    private final ItemRepository itemRepository;
    private final OutfitMapper mapper;

    @Autowired
    public OutfitService(OutfitRepository outfitRepository, ItemRepository itemRepository, OutfitMapper outfitMapper) {
        this.outfitRepository = outfitRepository;
        this.itemRepository = itemRepository;
        this.mapper = outfitMapper;
    }

    public List<OutfitDTO> getAllOutfits() {
        List<OutfitEntity> entities = outfitRepository.findAll();
        return entities.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public OutfitDTO getOutfitById(String id) {
        return outfitRepository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    public OutfitDTO createOutfit(OutfitDTO outfitDTO) {
        // Check if all items exist in repo
        List<String> nonExistingItems = outfitDTO.getItemIds().stream()
                .filter(itemId -> !itemRepository.existsById(itemId))
                .collect(Collectors.toList());

        // If any dont exist, throw exception
        if (!nonExistingItems.isEmpty()) {
            throw new ItemNotFoundException("Invalid itemIds: " + nonExistingItems);
        }

        OutfitEntity outfitEntity = mapper.toEntity(outfitDTO);
        outfitEntity = outfitRepository.save(outfitEntity);
        return mapper.toDTO(outfitEntity);
    }

    public void deleteOutfit(String id) {
        outfitRepository.deleteById(id);
    }

    public OutfitDTO updateOutfit(String id, OutfitDTO outfitDTO) {
        return outfitRepository.findById(id)
                .map(existingEntity -> {
                    existingEntity.setName(outfitDTO.getName());
                    existingEntity.setDescription(outfitDTO.getDescription());
                    existingEntity.setItemIds(outfitDTO.getItemIds());
                    OutfitEntity updatedEntity = outfitRepository.save(existingEntity);
                    return mapper.toDTO(updatedEntity);
                })
                .orElse(null);
    }
}
