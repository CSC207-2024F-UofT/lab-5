package use_case.search_recipe_list_by_ingredient;

/**
 * The Search Recipe List By Ingredient Use Case.
 */
public interface SearchRecipeListByIngredientInputBoundary {

    /**
     * Execute the Search Recipe List By Ingredient Use Case.
     * @param searchRecipeListByIngredientInputData the input data for this use case
     */
    void execute(SearchRecipeListByIngredientInputData searchRecipeListByIngredientInputData);

}
