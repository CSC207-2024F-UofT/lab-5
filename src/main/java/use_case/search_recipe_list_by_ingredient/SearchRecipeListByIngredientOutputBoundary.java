package use_case.search_recipe_list_by_ingredient;

/**
 * The output boundary for the Search Recipe List by Ingredient Use Case.
 */
public interface SearchRecipeListByIngredientOutputBoundary {
    /**
     * Prepares the success view for the Search Recipe List by Ingredient Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchRecipeListByIngredientOutputData outputData);

    /**
     * Prepares the failure view for the Search Recipe List by Ingredient Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
