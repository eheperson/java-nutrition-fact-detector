package com.localhost.nutritionfact.dao;

import com.localhost.nutritionfact.db.DbConnection;
import com.localhost.nutritionfact.model.Nutrition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NutritionDaoImp implements INutritionDao {

    @Override
    public void addNutrition(Nutrition nutrition) {
                String sql = "INSERT INTO nutrition (title, description, source_type, source_details) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nutrition.getTitle());
            pstmt.setString(2, nutrition.getDescription());
            pstmt.setString(3, nutrition.getSourceType());
            pstmt.setString(4, nutrition.getSourceDetails());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Nutrition getNutrition(int id) {
        String sql = "SELECT * FROM nutrition WHERE nutrition_id = ?";
        Nutrition nutrition = null;
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nutrition = new Nutrition();
                nutrition.setNutritionId(rs.getInt("nutrition_id"));
                nutrition.setTitle(rs.getString("title"));
                nutrition.setDescription(rs.getString("description"));
                nutrition.setSourceType(rs.getString("source_type"));
                nutrition.setSourceDetails(rs.getString("source_details"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nutrition;
    }

    @Override
    public List<Nutrition> getAllNutritions() {
        List<Nutrition> nutritions = new ArrayList<>();
        String sql = "SELECT * FROM nutrition";
        try (Connection conn = DbConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Nutrition nutrition = new Nutrition();
                nutrition.setNutritionId(rs.getInt("nutrition_id"));
                nutrition.setTitle(rs.getString("title"));
                nutrition.setDescription(rs.getString("description"));
                nutrition.setSourceType(rs.getString("source_type"));
                nutrition.setSourceDetails(rs.getString("source_details"));
                nutritions.add(nutrition);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nutritions;
    }

    @Override
    public void updateNutrition(Nutrition nutrition) {
        String sql = "UPDATE nutrition SET title = ?, description = ?, source_type = ?, source_details = ? WHERE nutrition_id = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nutrition.getTitle());
                pstmt.setString(2, nutrition.getDescription());
                pstmt.setString(3, nutrition.getSourceType());
                pstmt.setString(4, nutrition.getSourceDetails());
                pstmt.setInt(5, nutrition.getNutritionId());
                pstmt.executeUpdate();
                        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean deleteNutrition(int id) {
        String sql = "DELETE FROM nutrition WHERE nutrition_id = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    private Nutrition extractNutritionFromResultSet(ResultSet rs) throws SQLException {
        Nutrition nutrition = new Nutrition();
        nutrition.setNutritionId(rs.getInt("nutrition_id"));
        nutrition.setTitle(rs.getString("title"));
        nutrition.setDescription(rs.getString("description"));
        nutrition.setSourceType(rs.getString("source_type"));
        nutrition.setSourceDetails(rs.getString("source_details"));
        return nutrition;
    }
}