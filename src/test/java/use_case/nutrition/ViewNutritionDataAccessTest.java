package use_case.nutrition;

import data_access.ViewNutritionDataAccessImpl;
import entity.Nutrition;

import java.util.List;

public class ViewNutritionDataAccessTest {
    public static void main(String[] args) {
        // 创建一个 ViewNutritionDataAccessImpl 对象
        ViewNutritionDataAccessImpl dataAccess = new ViewNutritionDataAccessImpl();

        // 传入测试的 recipeId
        int recipeId = 1003464;

        // 调用方法获取营养数据
        List<Nutrition> nutritionList = dataAccess.getNutritionDataForRecipe(recipeId);

        // 输出结果
        if (!nutritionList.isEmpty()) {
            System.out.println("Nutrition data for recipe ID " + recipeId + ":");
            for (Nutrition nutrition : nutritionList) {
                System.out.println(nutrition);
            }
        } else {
            System.out.println("No nutrition data found for recipe ID " + recipeId + ".");
        }
    }
}
