package data_access;

import java.util.List;

import entity.Recipe;

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

    /**
     * Filter searches recipes.
     * @param ingredients list of ingredients
     * @param diet diet choice
     * @param cuisine cuisine choice
     * @return list of filtered recipes
     */
    List<Recipe> filterSearchRecipes(List<String> ingredients, String diet, String cuisine);

}

