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
    void updateNutrition(Nutrition nutrition);

    // Deletes a Nutrition object from the database by its ID
    void deleteNutrition(int id);
}
