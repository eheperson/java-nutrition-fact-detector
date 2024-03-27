package com.localhost.nutritionfact.dao;

import com.localhost.nutritionfact.db.DbConnection;
import com.localhost.nutritionfact.model.Nutrition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @file NutritionDaoImp.java
 * @brief Implementation of the INutritionDao interface for CRUD operations on Nutrition objects.
 * 
 * This class provides an implementation of the INutritionDao interface, offering methods to perform
 * CRUD (Create, Read, Update, Delete) operations on Nutrition objects in the database. It interacts
 * with the underlying database using JDBC (Java Database Connectivity) to execute SQL queries and
 * updates. Each method corresponds to a specific database operation and is responsible for managing
 * the database connection, executing SQL statements, and handling exceptions.
 * 
 * @author [Your Name]
 * @date [Date]
 */
public class NutritionDaoImp implements INutritionDao {

     /**
     * @brief Inserts a new Nutrition object into the database.
     * 
     * This method adds a new Nutrition object to the database by executing an SQL INSERT statement.
     * It takes a Nutrition object containing the details of the nutrition to be added and inserts
     * its attributes (title, description, source type, and source details) into the nutrition table.
     * The method returns true if the insertion was successful, or false otherwise.
     * 
     * @param nutrition The Nutrition object to be added to the database.
     * @return True if the nutrition was added successfully, false otherwise.
     */
    @Override
    public boolean addNutrition(Nutrition nutrition) {
        String sql = "INSERT INTO nutrition (title, description, source_type, source_details) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbConnection.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nutrition.getTitle());
            pstmt.setString(2, nutrition.getDescription());
            pstmt.setString(3, nutrition.getSourceType());
            pstmt.setString(4, nutrition.getSourceDetails());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            close(conn, pstmt, null);
        }
    }
    
     /**
     * @brief Retrieves a Nutrition object by its ID from the database.
     * 
     * This method retrieves a Nutrition object from the database based on its unique ID. It executes
     * an SQL SELECT statement with a WHERE clause to fetch the nutrition record with the specified ID.
     * If a matching record is found in the database, a Nutrition object is created and populated with
     * the retrieved data. If no matching record is found, the method returns null.
     * 
     * @param id The ID of the Nutrition object to retrieve.
     * @return The Nutrition object with the specified ID, or null if not found.
     */
    @Override
    public Nutrition getNutrition(int id) {
        String sql = "SELECT * FROM nutrition WHERE nutrition_id = ?";
        Nutrition nutrition = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DbConnection.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nutrition = extractNutritionFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(conn, pstmt, rs);
        }
        return nutrition;
    }

    /**
     * @brief Retrieves all Nutrition objects from the database.
     * 
     * This method retrieves all Nutrition objects stored in the database by executing an SQL SELECT
     * statement without any conditions. It fetches all rows from the nutrition table and creates
     * Nutrition objects for each row, adding them to a List. The List of Nutrition objects is then
     * returned as the result of the method.
     * 
     * @return A List containing all Nutrition objects stored in the database.
     */
    @Override
    public List<Nutrition> getAllNutritions() {
        List<Nutrition> nutritions = new ArrayList<>();
        String sql = "SELECT * FROM nutrition";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbConnection.getInstance().getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Nutrition nutrition = extractNutritionFromResultSet(rs);
                nutritions.add(nutrition);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(conn, stmt, rs);
        }
        return nutritions;
    }

    /**
     * @brief Updates an existing Nutrition object in the database.
     * 
     * This method updates an existing Nutrition object in the database with new information. It executes
     * an SQL UPDATE statement to modify the attributes of the nutrition record identified by its ID. The
     * Nutrition object passed as a parameter contains the updated attribute values. The method returns
     * true if the update was successful, or false otherwise.
     * 
     * @param nutrition The updated Nutrition object.
     * @return True if the update was successful, false otherwise.
     */
    @Override
    public boolean updateNutrition(Nutrition nutrition) {
        String sql = "UPDATE nutrition SET title = ?, description = ?, source_type = ?, source_details = ? WHERE nutrition_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbConnection.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nutrition.getTitle());
            pstmt.setString(2, nutrition.getDescription());
            pstmt.setString(3, nutrition.getSourceType());
            pstmt.setString(4, nutrition.getSourceDetails());
            pstmt.setInt(5, nutrition.getNutritionId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            close(conn, pstmt, null);
        }
    }

    /**
     * @brief Deletes a Nutrition object from the database by its ID.
     * 
     * This method deletes a Nutrition object from the database based on its unique ID. It executes an
     * SQL DELETE statement with a WHERE clause to remove the nutrition record with the specified ID.
     * If the deletion is successful (i.e., at least one row is affected), the method returns true;
     * otherwise, it returns false.
     * 
     * @param id The ID of the Nutrition object to delete.
     * @return True if the deletion was successful, false otherwise.
     */
    @Override
    public boolean deleteNutrition(int id) {
        String sql = "DELETE FROM nutrition WHERE nutrition_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbConnection.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            close(conn, pstmt, null);
        }
    }
    
    /**
     * @brief Extracts a Nutrition object from the current row of a ResultSet.
     * 
     * This method is a utility function used internally to extract a Nutrition object from the current
     * row of a ResultSet obtained from a database query. It constructs a Nutrition object and populates
     * its attributes with the data retrieved from the ResultSet. The populated Nutrition object is then
     * returned as the result of the method.
     * 
     * @param rs The ResultSet containing the data from which to extract the Nutrition object.
     * @return A fully populated Nutrition object based on the current row of the ResultSet.
     * @throws SQLException If a database access error occurs or this method is called on a closed ResultSet.
     */
    private Nutrition extractNutritionFromResultSet(ResultSet rs) throws SQLException {
        Nutrition nutrition = new Nutrition();
        nutrition.setNutritionId(rs.getInt("nutrition_id"));
        nutrition.setTitle(rs.getString("title"));
        nutrition.setDescription(rs.getString("description"));
        nutrition.setSourceType(rs.getString("source_type"));
        nutrition.setSourceDetails(rs.getString("source_details"));
        return nutrition;
    }

     /**
     * @brief Utility method to close database resources.
     * 
     * This method is a utility function used internally to close the database connection, statement, and
     * result set resources after executing a database operation. It helps in proper resource management
     * and ensures that database resources are released efficiently, preventing memory leaks and resource
     * exhaustion.
     * 
     * @param conn The Connection object representing the database connection.
     * @param stmt The Statement or PreparedStatement object used to execute SQL statements.
     * @param rs The ResultSet object containing the results of a database query.
     */
    // Utility method to close resources
    private void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
}