package com.example.backend.item;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<ItemEntity, String> {
    List<ItemEntity> findAllByCategory(ItemCategory category);
}
