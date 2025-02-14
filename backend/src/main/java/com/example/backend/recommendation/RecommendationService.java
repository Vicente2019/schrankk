package com.example.backend.recommendation;

import com.example.backend.exception.SchrankException;
import com.example.backend.item.ItemCategory;
import com.example.backend.item.ItemEntity;
import com.example.backend.item.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.backend.exception.ErrorMessage.ITEM_NOT_FOUND_CATEGORY;

@Service
public class RecommendationService {

    private final ItemRepository itemRepository;
    private final RecommendationLogRepository recommendationLogRepository;

    public RecommendationService(ItemRepository itemRepository, RecommendationLogRepository recommendationLogRepository) {
        this.itemRepository = itemRepository;
        this.recommendationLogRepository = recommendationLogRepository;
    }

    public ItemEntity recommendItem(ItemCategory category, List<String> tags) {
        List<ItemEntity> itemsInCategory = itemRepository.findAllByCategory(category);

        Optional<ItemEntity> bestItem = itemsInCategory.stream()
                .max((item1, item2) -> Integer.compare(countMatchingTags(item1.getTags(), tags), countMatchingTags(item2.getTags(), tags)));

        ItemEntity recommendedItem = bestItem.orElseThrow(() -> new SchrankException(ITEM_NOT_FOUND_CATEGORY, category));

        RecommendationLogEntity log = new RecommendationLogEntity(category.name(), tags, recommendedItem.getId());
        recommendationLogRepository.save(log);

        return recommendedItem;
    }


    private int countMatchingTags(List<String> itemTags, List<String> selectedTags) {
        return (int) itemTags.stream().filter(selectedTags::contains).count();
    }

}
