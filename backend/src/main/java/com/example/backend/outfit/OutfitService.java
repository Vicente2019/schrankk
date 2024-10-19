package com.example.backend.outfit;

import static com.example.backend.exception.ErrorMessage.*;
import com.example.backend.exception.SchrankException;
import com.example.backend.item.ItemRepository;
import com.example.backend.plannedOutfit.PlannedOutfitEntity;
import com.example.backend.plannedOutfit.PlannedOutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutfitService {

    private final OutfitRepository outfitRepository;
    private final ItemRepository itemRepository;
    private final PlannedOutfitRepository plannedOutfitRepository;
    private final OutfitMapper mapper;

    @Autowired
    public OutfitService(OutfitRepository outfitRepository, ItemRepository itemRepository, PlannedOutfitRepository plannedOutfitRepository, OutfitMapper outfitMapper) {
        this.outfitRepository = outfitRepository;
        this.itemRepository = itemRepository;
        this.plannedOutfitRepository = plannedOutfitRepository;
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
                .orElseThrow(() -> new SchrankException(OUTFIT_NOT_FOUND, id));
    }

    public OutfitDTO createOutfit(OutfitDTO outfitDTO) {
        validateItemIdsExist(outfitDTO.getItemIds());
        OutfitEntity outfitEntity = mapper.toEntity(outfitDTO);
        outfitEntity = outfitRepository.save(outfitEntity);
        return mapper.toDTO(outfitEntity);
    }

    public void deleteOutfit(String id) {
        if (!outfitRepository.existsById(id)) {
            throw new SchrankException(OUTFIT_NOT_FOUND, id);
        }
        List<PlannedOutfitEntity> plannedOutfits = plannedOutfitRepository.findAllByOutfitId(id);
        plannedOutfitRepository.deleteAll(plannedOutfits);

        outfitRepository.deleteById(id);
    }

    public OutfitDTO updateOutfit(String id, OutfitDTO outfitDTO) {
        OutfitEntity existingOutfit = outfitRepository.findById(id)
                .orElseThrow(() -> new SchrankException(OUTFIT_NOT_FOUND, id));

        validateItemIdsExist(outfitDTO.getItemIds());

        existingOutfit.setName(outfitDTO.getName());
        existingOutfit.setDescription(outfitDTO.getDescription());
        existingOutfit.setItemIds(outfitDTO.getItemIds());

        existingOutfit.verifyInvariants();
        OutfitEntity updatedOutfit = outfitRepository.save(existingOutfit);

        return mapper.toDTO(updatedOutfit);
    }

    public OutfitDTO addItemToOutfit(String outfitId, String itemId) {
        OutfitEntity outfit = outfitRepository.findById(outfitId)
                .orElseThrow(() -> new SchrankException(OUTFIT_NOT_FOUND, outfitId));

        if (!itemRepository.existsById(itemId)) {
            throw new SchrankException(ITEM_NOT_FOUND, itemId);
        }
        outfit.addItem(itemId);
        outfit.verifyInvariants();

        outfit = outfitRepository.save(outfit);
        return mapper.toDTO(outfit);
    }

    public OutfitDTO removeItemFromOutfit(String outfitId, String itemId) {
        OutfitEntity outfit = outfitRepository.findById(outfitId)
                .orElseThrow(() -> new SchrankException(OUTFIT_NOT_FOUND, outfitId));

        outfit.removeItem(itemId);
        outfit.verifyInvariants();

        outfit = outfitRepository.save(outfit);
        return mapper.toDTO(outfit);
    }


    private void validateItemIdsExist(List<String> itemIds) {
        List<String> nonExistingItems = itemIds.stream()
                .filter(itemId -> !itemRepository.existsById(itemId))
                .collect(Collectors.toList());
        if (!nonExistingItems.isEmpty()) {
            throw new SchrankException(ITEMS_NOT_EXIST, nonExistingItems);
        }
    }
}
