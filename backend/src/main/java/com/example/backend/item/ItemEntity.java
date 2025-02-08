package com.example.backend.item;

import com.example.backend.exception.SchrankException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.example.backend.exception.ErrorMessage.*;

@Document(collection = "items")
public class ItemEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String size;
    private String brand;
    private ItemCategory category;
    private List<String> tags;

    public ItemEntity() {}

    public ItemEntity(String name, String description, double price, String size, String brand, ItemCategory category, List<String> tags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.brand = brand;
        this.category = category;
        this.tags = tags;
        verifyInvariants();
    }

    public void verifyInvariants() {
        nameIsRequired();
        brandIsRequired();
        categoryIsRequired();
        priceIsPositive();
        atLeastThreeTags();
    }

    private void nameIsRequired() {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new SchrankException(ITEM_NAME_INVALID, this.name);
        }
    }

    private void brandIsRequired() {
        if (this.brand == null || this.brand.trim().isEmpty()) {
            throw new SchrankException(ITEM_BRAND_INVALID, this.brand);
        }
    }

    private void categoryIsRequired() {
        if (this.category == null) {
            throw new SchrankException(ITEM_CATEGORY_INVALID, this.category);
        }
    }

    private void priceIsPositive() {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new SchrankException(ITEM_PRICE_NEGATIVE, this.price);
        }
    }

    private void atLeastThreeTags() {
        if (this.tags == null || this.tags.size() < 3) {
            throw new SchrankException(ITEMS_NOT_ENOUGH_TAGS, this.tags);
        }
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}