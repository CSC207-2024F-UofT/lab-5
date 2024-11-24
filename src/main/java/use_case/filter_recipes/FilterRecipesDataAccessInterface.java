package use_case.filter_recipes;

import java.util.List;

import entity.CuisinePreference;
import entity.DietaryPreference;
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
    List<Recipe> filterRecipesByDiet(DietaryPreference diet);

    /**
     * Filters recipes by cuisine.
     * @param cuisine the cuisine preference
     * @return a list of recipes with that cuisine
     */
    List<Recipe> filterRecipesByCuisine(CuisinePreference cuisine);

}
