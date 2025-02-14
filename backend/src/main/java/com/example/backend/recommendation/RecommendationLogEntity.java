package com.example.backend.recommendation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "recommendation_logs")
public class RecommendationLogEntity {

    @Id
    private String id;
    private String category;
    private List<String> requestedTags;
    private String recommendedItemId;
    private Integer happinessScore; // null until user provides feedback

    public RecommendationLogEntity(String category, List<String> requestedTags, String recommendedItemId) {
        this.category = category;
        this.requestedTags = requestedTags;
        this.recommendedItemId = recommendedItemId;
        this.happinessScore = null; // No feedback yet
    }

    public void setHappinessScore(int score) {
        this.happinessScore = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getRequestedTags() {
        return requestedTags;
    }

    public void setRequestedTags(List<String> requestedTags) {
        this.requestedTags = requestedTags;
    }

    public String getRecommendedItemId() {
        return recommendedItemId;
    }

    public void setRecommendedItemId(String recommendedItemId) {
        this.recommendedItemId = recommendedItemId;
    }

    public Integer getHappinessScore() {
        return happinessScore;
    }

    public void setHappinessScore(Integer happinessScore) {
        this.happinessScore = happinessScore;
    }
}
