package use_case.filter_recipes;

import java.util.List;

/**
 * The filter recipes input boundary interface for calling the use case.
 */
public interface FilterRecipesInputBoundary {

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
     *
     * @param filterRecipesInputData input data
     */
    void filterSearchRecipes(FilterRecipesInputData filterRecipesInputData);
}
