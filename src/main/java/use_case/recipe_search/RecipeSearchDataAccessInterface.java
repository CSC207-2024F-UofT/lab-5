package use_case.recipe_search;

import java.util.List;

import entity.Recipe;

/**
 * DAO for the Search Use Case.
 */
public interface RecipeSearchDataAccessInterface {

    /**
     * Executes the Search Use Case.
     * @param recipeName the recipe name
     * @param calMin the minimum value for calories
     * @param calMax the maximum value for calories
     * @param carbMin the minimum value for carbohydrates
     * @param carbMax the maximum value for carbohydrates
     * @param proteinMin the minimum value for protein
     * @param proteinMax the maximum value for protein
     * @param fatMin the minimum value for fat
     * @param fatMax the maximum value for fat
     * @return recipeList a list of all recipes that match the filters
     */
    List<Recipe> searchRecipe(String recipeName, String calMin, String calMax, String carbMin, String carbMax, String proteinMin, String proteinMax, String fatMin, String fatMax);
}
