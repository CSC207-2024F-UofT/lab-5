package use_case.recipe_search;

import use_case.signup.SignupInputData;

public interface RecipeSearchInputBoundary {

    /**
     * Executes the search use case.
     * @param recipeSearchInputData the input data
     */
    void execute(RecipeSearchInputData recipeSearchInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToSearchResultsView();
}
