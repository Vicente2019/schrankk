package com.example.backend.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public List<ItemDTO> getAllItems() {
        List<ItemEntity> entities = itemRepository.findAll();
        return entities.stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ItemDTO getItemById(String id) {
        return itemRepository.findById(id)
                .map(itemMapper::toDTO)
                .orElse(null);
    }

    public ItemDTO createItem(ItemDTO itemDTO) {
        ItemEntity itemEntity = itemMapper.toEntity(itemDTO);
        ItemEntity savedEntity = itemRepository.save(itemEntity);
        return itemMapper.toDTO(savedEntity);
    }

    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }

    public ItemDTO updateItem(String id, ItemDTO itemDTO) {
        return itemRepository.findById(id)
                .map(existingEntity -> {
                    // Update fields
                    existingEntity.setName(itemDTO.getName());
                    existingEntity.setDescription(itemDTO.getDescription());
                    existingEntity.setPrice(itemDTO.getPrice());
                    existingEntity.setSize(itemDTO.getSize());
                    existingEntity.setBrand(itemDTO.getBrand());
                    existingEntity.setCategory(itemDTO.getCategory());
                    existingEntity.setTags(itemDTO.getTags());
                    ItemEntity updatedEntity = itemRepository.save(existingEntity);
                    return itemMapper.toDTO(updatedEntity);
                })
                .orElse(null);
    }
}
