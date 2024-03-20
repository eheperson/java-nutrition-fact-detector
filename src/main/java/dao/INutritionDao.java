package dao;

import model.Nutrition;
import java.util.List;

public interface INutritionDao {
    void addNutrition(Nutrition nutrition);
    Nutrition getNutritionById(int id);
    List<Nutrition> getAllNutritions();
    void updateNutrition(Nutrition nutrition);
    void deleteNutrition(int id);
}
