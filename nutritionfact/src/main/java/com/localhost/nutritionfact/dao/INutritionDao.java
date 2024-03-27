package com.localhost.nutritionfact.dao;

import com.localhost.nutritionfact.model.Nutrition;

import java.util.List;

public interface INutritionDao {
    // Adds a new Nutrition object to the database
    void addNutrition(Nutrition nutrition);

    // Retrieves a Nutrition object by its ID
    Nutrition getNutrition(int id);

    // Retrieves all Nutrition objects from the database
    List<Nutrition> getAllNutritions();

    // Updates an existing Nutrition object in the database
    boolean updateNutrition(Nutrition nutrition);

    // Deletes a Nutrition object from the database by its ID
    boolean deleteNutrition(int id);
}
