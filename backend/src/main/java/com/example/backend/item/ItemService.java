package com.example.backend.item;

import com.example.backend.exception.ErrorMessage;
import com.example.backend.exception.SchrankException;
import com.example.backend.outfit.OutfitEntity;
import com.example.backend.outfit.OutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository repository;
    private final ItemMapper mapper;
    private final OutfitRepository outfitRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, OutfitRepository outfitRepository) {
        this.repository = itemRepository;
        this.mapper = itemMapper;
        this.outfitRepository = outfitRepository;
    }

    public List<ItemDTO> getAllItems() {
        List<ItemEntity> entities = repository.findAll();
        return entities.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public ItemDTO getItemById(String id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new SchrankException(ErrorMessage.ITEM_NOT_FOUND, id));
    }

    public ItemDTO createItem(ItemDTO itemDTO) {
        ItemEntity itemEntity = mapper.toEntity(itemDTO);
        itemEntity.verifyInvariants();
        ItemEntity savedEntity = repository.save(itemEntity);
        return mapper.toDTO(savedEntity);
    }

    @Transactional
    public void deleteItem(String id) {
        if (!repository.existsById(id)) {
            throw new SchrankException(ErrorMessage.ITEM_NOT_FOUND, id);
        }
        List<OutfitEntity> outfits = outfitRepository.findAllByItemIdsContains(id);
        for (OutfitEntity outfit : outfits) {
            outfit.removeItem(id);
            outfitRepository.save(outfit);
        }
        repository.deleteById(id);
    }

    public ItemDTO updateItem(String id, ItemDTO itemDTO) {
        return repository.findById(id)
                .map(existingEntity -> {
                    existingEntity.setName(itemDTO.getName());
                    existingEntity.setDescription(itemDTO.getDescription());
                    existingEntity.setPrice(itemDTO.getPrice());
                    existingEntity.setSize(itemDTO.getSize());
                    existingEntity.setBrand(itemDTO.getBrand());
                    existingEntity.setCategory(itemDTO.getCategory());
                    existingEntity.setTags(itemDTO.getTags());
                    ItemEntity updatedEntity = repository.save(existingEntity);
                    return mapper.toDTO(updatedEntity);
                })
                .orElseThrow(() -> new SchrankException(ErrorMessage.ITEM_NOT_FOUND, id));
    }
}
