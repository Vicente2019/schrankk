package com.example.backend.plannedOutfit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/planned-outfits")
public class PlannedOutfitController {

    private final PlannedOutfitService plannedOutfitService;

    @Autowired
    public PlannedOutfitController(PlannedOutfitService plannedOutfitService) {
        this.plannedOutfitService = plannedOutfitService;
    }

    @GetMapping
    public ResponseEntity<List<PlannedOutfitDTO>> getAllPlannedOutfits() {
        List<PlannedOutfitDTO> outfits = plannedOutfitService.getAllPlannedOutfits();
        return ResponseEntity.ok(outfits);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<PlannedOutfitDTO> getPlannedOutfitByDate(@PathVariable String date) {
        LocalDate plannedDate = LocalDate.parse(date);
        PlannedOutfitDTO plannedOutfit = plannedOutfitService.getPlannedOutfitByDate(plannedDate);
        return ResponseEntity.ok(plannedOutfit);
    }

    @PostMapping
    public ResponseEntity<PlannedOutfitDTO> planOutfit(@RequestParam String outfitId, @RequestParam String date) {
        LocalDate plannedDate = LocalDate.parse(date);
        PlannedOutfitDTO plannedOutfit = plannedOutfitService.planOutfit(outfitId, plannedDate);
        return ResponseEntity.ok(plannedOutfit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlannedOutfit(@PathVariable String id) {
        plannedOutfitService.deletePlannedOutfit(id);
        return ResponseEntity.noContent().build();
    }
}
