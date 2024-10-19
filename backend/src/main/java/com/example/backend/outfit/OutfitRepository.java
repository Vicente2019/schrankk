package com.example.backend.outfit;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OutfitRepository extends MongoRepository<OutfitEntity, String> {
    List<OutfitEntity> findAllByItemIdsContains(String itemId);

}
