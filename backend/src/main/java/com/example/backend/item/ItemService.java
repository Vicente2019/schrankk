package com.example.backend.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository repository;
    private final ItemMapper mapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.repository = itemRepository;
        this.mapper = itemMapper;
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
                .orElse(null);
    }

    public ItemDTO createItem(ItemDTO itemDTO) {
        ItemEntity itemEntity = mapper.toEntity(itemDTO);
        ItemEntity savedEntity = repository.save(itemEntity);
        return mapper.toDTO(savedEntity);
    }

    public void deleteItem(String id) {
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
                .orElse(null);
    }
}
