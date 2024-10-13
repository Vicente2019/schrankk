package com.example.backend.outfit;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutfitRepository extends MongoRepository<OutfitEntity, String> {
}
