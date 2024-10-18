package com.example.backend.outfit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outfits")
public class OutfitController {

    private final OutfitService outfitService;

    public OutfitController(OutfitService outfitService) {
        this.outfitService = outfitService;
    }

    @GetMapping
    public ResponseEntity<List<OutfitDTO>> getAllOutfits() {
        List<OutfitDTO> outfits = outfitService.getAllOutfits();
        return ResponseEntity.ok(outfits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutfitDTO> getOutfitById(@PathVariable String id) {
        OutfitDTO outfit = outfitService.getOutfitById(id);
        return ResponseEntity.ok(outfit);
    }

    @PostMapping
    public ResponseEntity<OutfitDTO> createOutfit(@RequestBody OutfitDTO outfitDTO) {
        OutfitDTO createdOutfit = outfitService.createOutfit(outfitDTO);
        return ResponseEntity.ok(createdOutfit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutfitDTO> updateOutfit(@PathVariable String id, @RequestBody OutfitDTO outfitDTO) {
        OutfitDTO updatedOutfit = outfitService.updateOutfit(id, outfitDTO);
        return ResponseEntity.ok(updatedOutfit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutfit(@PathVariable String id) {
        outfitService.deleteOutfit(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{outfitId}/items/{itemId}")
    public ResponseEntity<OutfitDTO> addItemToOutfit(@PathVariable String outfitId, @PathVariable String itemId) {
        OutfitDTO updatedOutfit = outfitService.addItemToOutfit(outfitId, itemId);
        return ResponseEntity.ok(updatedOutfit);
    }

    @DeleteMapping("/{outfitId}/items/{itemId}")
    public ResponseEntity<OutfitDTO> removeItemFromOutfit(@PathVariable String outfitId, @PathVariable String itemId) {
        OutfitDTO updatedOutfit = outfitService.removeItemFromOutfit(outfitId, itemId);
        return ResponseEntity.ok(updatedOutfit);
    }
}
