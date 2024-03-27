package com.localhost.nutritionfact.dao;

import com.localhost.nutritionfact.model.Nutrition;

import java.util.List;

/**
 * @file INutritionDao.java
 * @brief Interface for performing CRUD operations on Nutrition objects in the database.
 * 
 * This interface defines methods for performing CRUD (Create, Read, Update, Delete) operations
 * on Nutrition objects stored in the database. Implementing classes are responsible for providing
 * concrete implementations of these methods to interact with the underlying database.
 * 
 * Each method corresponds to a specific database operation:
 * - addNutrition() inserts a new Nutrition object into the database.
 * - getNutrition() retrieves a Nutrition object by its ID from the database.
 * - getAllNutritions() retrieves all Nutrition objects stored in the database.
 * - updateNutrition() updates an existing Nutrition object in the database.
 * - deleteNutrition() deletes a Nutrition object from the database by its ID.
 * 
 * Implementing classes should handle database connectivity, SQL queries, and error handling
 * according to the requirements of the underlying database system. They should ensure proper
 * resource management and error reporting to maintain the integrity and reliability of database
 * operations.
 * 
 * @note Implementing classes should adhere to the contract defined by this interface,
 *       ensuring consistent behavior across different implementations while interacting
 *       with Nutrition objects in the database.
 * 
 * @author [Your Name]
 * @date [Date]
 */
public interface INutritionDao {
     /**
     * @brief Adds a new Nutrition object to the database.
     * 
     * This method inserts a new Nutrition object into the database. It takes a Nutrition
     * object as a parameter containing the details of the nutrition to be added. The
     * method returns true if the insertion was successful, or false otherwise.
     * 
     * @param nutrition The Nutrition object to be added to the database.
     * @return True if the nutrition was added successfully, false otherwise.
     */
    // Adds a new Nutrition object to the database
    boolean addNutrition(Nutrition nutrition);

    /**
     * @brief Retrieves a Nutrition object by its ID from the database.
     * 
     * This method retrieves a Nutrition object from the database based on its unique ID.
     * It takes an integer ID as a parameter and returns the corresponding Nutrition object
     * if found, or null if no such object exists in the database.
     * 
     * @param id The ID of the Nutrition object to retrieve.
     * @return The Nutrition object with the specified ID, or null if not found.
     */
    // Retrieves a Nutrition object by its ID
    Nutrition getNutrition(int id);

     /**
     * @brief Retrieves all Nutrition objects from the database.
     * 
     * This method retrieves all Nutrition objects stored in the database and returns them
     * as a List. If no Nutrition objects are found, an empty List is returned.
     * 
     * @return A List containing all Nutrition objects stored in the database.
     */
    // Retrieves all Nutrition objects from the database
    List<Nutrition> getAllNutritions();

     /**
     * @brief Updates an existing Nutrition object in the database.
     * 
     * This method updates an existing Nutrition object in the database with new information.
     * It takes a Nutrition object containing the updated information as a parameter and
     * returns true if the update was successful, or false otherwise.
     * 
     * @param nutrition The updated Nutrition object.
     * @return True if the update was successful, false otherwise.
     */
    // Updates an existing Nutrition object in the database
    boolean updateNutrition(Nutrition nutrition);

    /**
     * @brief Deletes a Nutrition object from the database by its ID.
     * 
     * This method deletes a Nutrition object from the database based on its unique ID.
     * It takes an integer ID as a parameter and returns true if the deletion was successful,
     * or false otherwise.
     * 
     * @param id The ID of the Nutrition object to delete.
     * @return True if the deletion was successful, false otherwise.
     */
    // Deletes a Nutrition object from the database by its ID
    boolean deleteNutrition(int id);
}
