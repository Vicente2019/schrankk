package com.example.backend.plannedOutfit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "planned_outfits")
public class PlannedOutfitEntity {

    @Id
    private String id;
    private String outfitId;
    private LocalDate plannedDate;

    public PlannedOutfitEntity() {
    }

    public PlannedOutfitEntity(String outfitId, LocalDate plannedDate) {
        this.outfitId = outfitId;
        this.plannedDate = plannedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutfitId() {
        return outfitId;
    }

    public void setOutfitId(String outfitId) {
        this.outfitId = outfitId;
    }

    public LocalDate getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(LocalDate plannedDate) {
        this.plannedDate = plannedDate;
    }
}
