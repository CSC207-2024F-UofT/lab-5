package use_case.recipe_search;

import entity.Recipe;

import java.util.List;

public interface RecipeSearchDataAccessInterface {

    List<Recipe> searchRecipe(String q, String cal_min, String cal_max, String carb_min, String carb_max, String protein_min, String protein_max, String fat_min, String fat_max);
}
