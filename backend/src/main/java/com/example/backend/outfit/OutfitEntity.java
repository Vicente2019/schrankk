package com.example.backend.outfit;

import com.example.backend.exception.SchrankException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

import static com.example.backend.exception.ErrorMessage.*;

@Document(collection = "outfits")
public class OutfitEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private List<String> itemIds;

    public OutfitEntity() {}

    public OutfitEntity(String name, String description, List<String> itemIds) {
        this.name = name;
        this.description = description;
        this.itemIds = itemIds;
        verifyInvariants();
    }

    public void verifyInvariants() {
        nameIsRequired();
        descriptionIsRequired();
        itemIdsIsRequired();
    }

    private void nameIsRequired() {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new SchrankException(OUTFIT_NAME_INVALID, this.name);
        }
    }

    private void descriptionIsRequired() {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new SchrankException(OUTFIT_DESCRIPTION_INVALID, this.name);
        }
    }

    private void itemIdsIsRequired() {
        if (this.itemIds == null || this.itemIds.isEmpty()) {
            throw new SchrankException(OUTFIT_MUST_HAVE_ITEMS);
        }
    }

    public void addItem(String itemId) {
        if (itemId == null || itemId.isEmpty()) {
            throw new SchrankException(INVALID_ITEM_ID, itemId);
        }
        if (itemIds.contains(itemId)) {
            throw new SchrankException(ITEM_ALREADY_IN_OUTFIT, itemId);
        }
        this.itemIds.add(itemId);
    }

    public void removeItem(String itemId) {
        if (!itemIds.contains(itemId)) {
            throw new SchrankException(ITEM_NOT_FOUND_IN_OUTFIT, itemId);
        }
        this.itemIds.remove(itemId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }
}
