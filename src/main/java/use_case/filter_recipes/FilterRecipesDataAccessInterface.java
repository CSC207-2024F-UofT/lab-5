package use_case.filter_recipes;

import java.util.List;

import entity.Recipe;

/**
 * DAO for the filter recipes use case.
 */
public interface FilterRecipesDataAccessInterface {

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
