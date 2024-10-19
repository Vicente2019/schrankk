package com.example.backend.plannedOutfit;

import static com.example.backend.exception.ErrorMessage.*;
import com.example.backend.exception.SchrankException;
import com.example.backend.outfit.OutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (plannedDate.isBefore(LocalDate.now())) {
            throw new SchrankException(PLANNED_DATE_CANNOT_BE_IN_THE_PAST, plannedDate.toString());
        }
        Optional<PlannedOutfitEntity> existingPlannedOutfit = plannedOutfitRepository.findByPlannedDateAndOutfitId(plannedDate, outfitId);
        if (existingPlannedOutfit.isPresent()) { // if the same outfit is already on that date
            throw new SchrankException(OUTFIT_ALREADY_PLANNED_FOR_THIS_DATE, outfitId);
        }

        PlannedOutfitEntity plannedOutfitEntity = new PlannedOutfitEntity(outfitId, plannedDate);
        plannedOutfitEntity = plannedOutfitRepository.save(plannedOutfitEntity);

        return plannedOutfitMapper.toDTO(plannedOutfitEntity);
    }

    public List<PlannedOutfitDTO> getAllPlannedOutfits() {
        List<PlannedOutfitEntity> plannedOutfitEntities = plannedOutfitRepository.findAll();
        return plannedOutfitEntities.stream().map(plannedOutfitMapper::toDTO).collect(Collectors.toList());
    }

    public PlannedOutfitDTO getPlannedOutfitByDate(LocalDate date) {
        return plannedOutfitRepository.findByPlannedDate(date)
                .map(plannedOutfitMapper::toDTO)
                .orElseThrow(() -> new SchrankException(PLANNED_OUTFIT_NOT_FOUND_DATE, date.toString()));
    }

    public void deletePlannedOutfit(String id) {
        if (!plannedOutfitRepository.existsById(id)) {
            throw new SchrankException(PLANNED_OUTFIT_NOT_FOUND_ID, id);
        }
        plannedOutfitRepository.deleteById(id);
    }
}
