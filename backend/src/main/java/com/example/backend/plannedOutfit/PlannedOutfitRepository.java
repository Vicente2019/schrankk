package com.example.backend.plannedOutfit;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface PlannedOutfitRepository extends MongoRepository<PlannedOutfitEntity, String> {
    Optional<PlannedOutfitEntity> findByPlannedDate(LocalDate plannedDate);
}
