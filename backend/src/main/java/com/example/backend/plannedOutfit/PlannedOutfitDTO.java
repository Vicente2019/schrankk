package com.example.backend.plannedOutfit;

import java.time.LocalDate;

public class PlannedOutfitDTO {

    private String id;
    private String outfitId;
    private LocalDate plannedDate;

    public PlannedOutfitDTO(String id, String outfitId, LocalDate plannedDate) {
        this.id = id;
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
