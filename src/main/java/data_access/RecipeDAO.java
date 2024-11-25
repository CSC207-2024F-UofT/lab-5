package data_access;

import entity.Recipe;

import java.util.List;

public interface RecipeDAO {
    List<Recipe> getRecipesByIngredients(List<String> ingredients);

    List<Recipe> getAllRecipes();

    /**
     * Get diets from API.
     * @return list of diets
     */
    List<String> getAvailableDiets();

    /**
     * Get cuisines from API.
     * @return list of cuisines
     */
    List<String> getAvailableCuisines();

}

