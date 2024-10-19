package com.example.backend.plannedOutfit;

import static com.example.backend.exception.ErrorMessage.*;
import com.example.backend.exception.SchrankException;
import com.example.backend.outfit.OutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PlannedOutfitService {

    private final PlannedOutfitRepository plannedOutfitRepository;
    private final OutfitRepository outfitRepository;
    private final PlannedOutfitMapper plannedOutfitMapper;

    @Autowired
    public PlannedOutfitService(PlannedOutfitRepository plannedOutfitRepository, OutfitRepository outfitRepository, PlannedOutfitMapper plannedOutfitMapper) {
        this.plannedOutfitRepository = plannedOutfitRepository;
        this.outfitRepository = outfitRepository;
        this.plannedOutfitMapper = plannedOutfitMapper;
    }

    public PlannedOutfitDTO planOutfit(String outfitId, LocalDate plannedDate) {
        if (!outfitRepository.existsById(outfitId)) {
            throw new SchrankException(OUTFIT_NOT_FOUND, outfitId);
        }

        PlannedOutfitEntity plannedOutfitEntity = new PlannedOutfitEntity(outfitId, plannedDate);
        plannedOutfitEntity = plannedOutfitRepository.save(plannedOutfitEntity);

        return plannedOutfitMapper.toDTO(plannedOutfitEntity);
    }
}
