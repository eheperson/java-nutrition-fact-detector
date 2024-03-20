package dao;

import model.Nutrition;
import util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NutritionDaoImpl implements INutritionDao {
    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public void addNutrition(Nutrition nutrition) {
        // Implementation omitted for brevity
    }

    // Other methods implemented similarly
}
