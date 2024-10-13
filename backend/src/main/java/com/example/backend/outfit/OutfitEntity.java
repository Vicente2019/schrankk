package com.example.backend.outfit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

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
