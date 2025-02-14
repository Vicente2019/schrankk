package com.example.backend.recommendation;

import com.example.backend.item.ItemCategory;
import com.example.backend.item.ItemDTO;
import com.example.backend.item.ItemEntity;
import com.example.backend.item.ItemMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final ItemMapper itemMapper;

    public RecommendationController(RecommendationService recommendationService, ItemMapper itemMapper) {
        this.recommendationService = recommendationService;
        this.itemMapper = itemMapper;
    }

    @GetMapping("/{category}")
    public ResponseEntity<ItemDTO> recommendItem(
            @PathVariable ItemCategory category,
            @RequestParam List<String> tags) {

        ItemEntity bestItem = recommendationService.recommendItem(category, tags);
        return ResponseEntity.ok(itemMapper.map(bestItem));
    }
}
