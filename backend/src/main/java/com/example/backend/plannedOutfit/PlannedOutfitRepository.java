package com.example.backend.plannedOutfit;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlannedOutfitRepository extends MongoRepository<PlannedOutfitEntity, String> {
    Optional<PlannedOutfitEntity> findByPlannedDate(LocalDate plannedDate);

    List<PlannedOutfitEntity> findAllByOutfitId(String id);

    Optional<PlannedOutfitEntity> findByPlannedDateAndOutfitId(LocalDate plannedDate, String outfitId);
}
