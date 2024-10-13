package com.example.backend.outfit;

import java.util.List;

public class OutfitDTO {

    private String id;
    private String name;
    private String description;
    private List<String> itemIds;

    public OutfitDTO(String id, String name, String description, List<String> itemIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.itemIds = itemIds;
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
