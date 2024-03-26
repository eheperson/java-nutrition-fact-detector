package com.localhost.nutritionfact.model;

public class Nutrition {
    private int nutritionId;
    private String title;
    private String description;
    private String sourceType; // Could be "Video", "BlogArticle", "MedicalInfo"
    private String sourceDetails; // This could store JSON or another structured string with details about the source

    // Default constructor
    public Nutrition() {
    }

    // Constructor with all fields
    public Nutrition(int nutritionId, String title, String description, String sourceType, String sourceDetails) {
        this.nutritionId = nutritionId;
        this.title = title;
        this.description = description;
        this.sourceType = sourceType;
        this.sourceDetails = sourceDetails;
    }

    // Getters and setters
    public int getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(int nutritionId) {
        this.nutritionId = nutritionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceDetails() {
        return sourceDetails;
    }

    public void setSourceDetails(String sourceDetails) {
        this.sourceDetails = sourceDetails;
    }

    // toString method for printing nutrition facts details
    @Override
    public String toString() {
        return "Nutrition{" +
                "nutritionId=" + nutritionId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", sourceDetails='" + sourceDetails + '\'' +
                '}';
    }
}