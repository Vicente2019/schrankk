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

    public RecommendationService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemEntity recommendItem(ItemCategory category, List<String> tags) {
        List<ItemEntity> itemsInCategory = itemRepository.findAllByCategory(category);

        Optional<ItemEntity> bestItem = itemsInCategory.stream()
                .max((item1, item2) -> Integer.compare(countMatchingTags(item1.getTags(), tags), countMatchingTags(item2.getTags(), tags)));

        return bestItem.orElseThrow(() -> new SchrankException(ITEM_NOT_FOUND_CATEGORY, category));
    }

    private int countMatchingTags(List<String> itemTags, List<String> selectedTags) {
        return (int) itemTags.stream().filter(selectedTags::contains).count();
    }

}
