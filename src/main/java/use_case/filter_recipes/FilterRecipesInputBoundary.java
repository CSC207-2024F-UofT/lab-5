package use_case.filter_recipes;

import entity.CuisinePreference;
import entity.DietaryPreference;

/**
 * The filter recipes input boundary interface for calling the use case.
 */
public interface FilterRecipesInputBoundary {

    /**
     * Filters recipes by diet.
     * @param diet the diet preference
     */
    void filterRecipesByDiet(DietaryPreference diet);

    /**
     * Filters recipes by cuisine.
     * @param cuisine the cuisine preference
     */
    void filterRecipesByCuisine(CuisinePreference cuisine);
}
