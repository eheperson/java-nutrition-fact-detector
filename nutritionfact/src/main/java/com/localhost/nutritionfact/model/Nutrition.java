package com.localhost.nutritionfact.model;

/**
 * @file Nutrition.java
 * @brief Defines the Nutrition class representing a nutrition fact.
 * 
 * This class represents a nutrition fact, storing information such as its ID, title,
 * description, source type, and source details. It provides constructors to initialize
 * the object with or without these details, along with getter and setter methods to
 * access and modify the object's attributes. Additionally, it overrides the toString
 * method to provide a string representation of the nutrition fact for printing purposes.
 * 
 * @author [Your Name]
 * @date [Date]
 */
public class Nutrition {
    private int nutritionId;
    private String title;
    private String description;
    private String sourceType; // Could be "Video", "BlogArticle", "MedicalInfo"
    private String sourceDetails; // This could store JSON or another structured string with details about the source

    /**
     * @brief Default constructor for the Nutrition class.
     * 
     * This constructor initializes a new Nutrition object with default values.
     */
    // Default constructor
    public Nutrition() {
    }

    /**
     * @brief Constructor for the Nutrition class with all fields.
     * 
     * This constructor initializes a new Nutrition object with the provided values
     * for its attributes.
     * 
     * @param nutritionId The ID of the nutrition fact.
     * @param title The title or name of the nutrition fact.
     * @param description A description or summary of the nutrition fact.
     * @param sourceType The type of the source (e.g., "Video", "BlogArticle", "MedicalInfo").
     * @param sourceDetails Additional details about the source (e.g., JSON or structured string).
     */
    // Constructor with all fields
    public Nutrition(int nutritionId, String title, String description, String sourceType, String sourceDetails) {
        this.nutritionId = nutritionId;
        this.title = title;
        this.description = description;
        this.sourceType = sourceType;
        this.sourceDetails = sourceDetails;
    }

    // Getters and setters

    /**
     * @brief Getter for the nutrition ID.
     * 
     * @return The ID of the nutrition fact.
     */
    public int getNutritionId() {
        return nutritionId;
    }

    /**
     * @brief Setter for the nutrition ID.
     * 
     * @param nutritionId The ID of the nutrition fact to set.
     */
    public void setNutritionId(int nutritionId) {
        this.nutritionId = nutritionId;
    }

    /**
     * @brief Getter for the title.
     * 
     * @return The title or name of the nutrition fact.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @brief Setter for the title.
     * 
     * @param title The title or name of the nutrition fact to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @brief Getter for the description.
     * 
     * @return A description or summary of the nutrition fact.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @brief Setter for the description.
     * 
     * @param description A description or summary of the nutrition fact to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @brief Getter for the source type.
     * 
     * @return The type of the source (e.g., "Video", "BlogArticle", "MedicalInfo").
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * @brief Setter for the source type.
     * 
     * @param sourceType The type of the source to set (e.g., "Video", "BlogArticle", "MedicalInfo").
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * @brief Getter for the source details.
     * 
     * @return Additional details about the source (e.g., JSON or structured string).
     */
    public String getSourceDetails() {
        return sourceDetails;
    }

    /**
     * @brief Setter for the source details.
     * 
     * @param sourceDetails Additional details about the source to set (e.g., JSON or structured string).
     */
    public void setSourceDetails(String sourceDetails) {
        this.sourceDetails = sourceDetails;
    }

    // toString method for printing nutrition facts details
    
    /**
     * @brief Override of the toString method to provide a string representation of the Nutrition object.
     * 
     * @return A string representation of the Nutrition object, including its ID, title, description,
     *         source type, and source details.
     */
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