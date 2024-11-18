package use_case;

import java.util.ArrayList;
import java.util.List;

import data_access.RecipeDAO;
import entity.DietaryPreference;
import entity.Recipe;

/**
 * Use case layer for filtering recipes.
 */
public class FilterRecipesUseCase {
    // to be implemented
    // database from api?
    private final RecipeDAO recipeDao;

    public FilterRecipesUseCase(RecipeDAO recipeDao) {
        this.recipeDao = recipeDao;
    }

    /**
     * Get the recipes according to the dietary preference.
     * @param diet user's diet choice
     * @return a list of recipes that satisfy diet choice
     */
    public List<Recipe> filterByDiet(String diet) {
        final List<Recipe> recipes = recipeDao.getAllRecipes();
        final List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getDietaryType().equals(diet)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

}
