package use_case;

import java.util.List;

import entity.Nutrition;

public interface ViewNutritionDataAccessInterface {

    List<Nutrition> getNutritionDataForRecipe(int recipeId);
}
