package com.localhost.nutritionfact.dao;

import com.localhost.nutritionfact.db.DbConnection;
import com.localhost.nutritionfact.model.Nutrition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NutritionDaoImp implements INutritionDao {

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
    
    private Nutrition extractNutritionFromResultSet(ResultSet rs) throws SQLException {
        Nutrition nutrition = new Nutrition();
        nutrition.setNutritionId(rs.getInt("nutrition_id"));
        nutrition.setTitle(rs.getString("title"));
        nutrition.setDescription(rs.getString("description"));
        nutrition.setSourceType(rs.getString("source_type"));
        nutrition.setSourceDetails(rs.getString("source_details"));
        return nutrition;
    }

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