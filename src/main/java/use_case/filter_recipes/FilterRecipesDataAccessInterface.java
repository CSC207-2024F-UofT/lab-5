package use_case.filter_recipes;

import java.util.List;

import entity.Recipe;

/**
 * DAO for the filter recipes use case.
 */
public interface FilterRecipesDataAccessInterface {

    /**
     * Filters recipes by diet.
     * @param diet the diet preference
     * @return a list of recipes with that diet
     */
    // List<Recipe> filterRecipesByDiet(DietaryPreference diet);

    /**
     * Filters recipes by cuisine.
     * @param cuisine the cuisine preference
     * @return a list of recipes with that cuisine
     */
    // List<Recipe> filterRecipesByCuisine(CuisinePreference cuisine);

    /**
     * Filters recipes by ingredients, optional diet and cuisine.
     * @param ingredients list of ingredients
     * @param diet diet choice, optional
     * @param cuisine cuisine choice, optional
     * @return a list of filtered recipes
     */
    List<Recipe> filterSearchRecipes(List<String> ingredients, String diet, String cuisine);

    /**
     * Get available diets.
     * @return list of diets
     */
    List<String> getAvailableDiets();

    /**
     * Get available cuisines.
     * @return list of cuisines
     */
    List<String> getAvailableCuisines();

}
