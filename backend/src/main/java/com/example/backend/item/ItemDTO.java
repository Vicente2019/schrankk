package com.example.backend.item;

import java.util.List;

public class ItemDTO {

    private String name;
    private String description;
    private double price;
    private String size;
    private String brand;
    private String category;
    private List<String> tags;

    public ItemDTO() {}

    public ItemDTO(String name, String description, double price, String size, String brand, String category, List<String> tags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.brand = brand;
        this.category = category;
        this.tags = tags;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
