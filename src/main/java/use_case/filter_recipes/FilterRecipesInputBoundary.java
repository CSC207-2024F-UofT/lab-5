package use_case.filter_recipes;

import java.util.List;

import entity.Recipe;

/**
 * The filter recipes input boundary interface for calling the use case.
 */
public interface FilterRecipesInputBoundary {

    // /**
    //  * Execute the filter recipes use case.
    //  * @param filterRecipesInputData the input data for this use case
    //  */
    // void execute(FilterRecipesInputData filterRecipesInputData);

    /**
     * Get available diets.
     * @return list of available diets
     */
    List<String> getAvailableDiets();

    /**
     * Get available cuisines.
     * @return list of available cuisines
     */
    List<String> getAvailableCuisines();

    /**
     * Filter searches recipes.
     * @param ingredients list of ingredients
     * @param diet diet choice
     * @param cuisine cuisine choice
     * @return list of recipes
     */
    List<Recipe> filterSearchRecipes(List<String> ingredients, String diet, String cuisine);
}
