package com.example.backend.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    public ItemEntity getItemById(String id) {
        return itemRepository.findById(id).orElse(null);
    }

    public ItemEntity createItem(ItemEntity item) {
        return itemRepository.save(item);
    }

    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }
}
