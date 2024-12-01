package use_case.recipe_search;

/**
 * Input Boundary for actions which are related to searching.
 */
public interface RecipeSearchInputBoundary {

    /**
     * Executes the search use case.
     * @param recipeSearchInputData the input data
     */
    void execute(RecipeSearchInputData recipeSearchInputData);

    /**
     * Executes the switch to search view use case.
     */
    void switchToSearchResultsView();

    /**
     * Executes the switch to Profile view.
     */
    void switchToProfileView();
}
