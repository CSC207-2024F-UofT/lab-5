package interface_adapter.recipe_search;

import use_case.recipe_search.RecipeSearchInputBoundary;
import use_case.recipe_search.RecipeSearchInputData;

/**
 * The controller for the Search Use Case.
 */
public class RecipeSearchController {

    private final RecipeSearchInputBoundary searchUseCaseInteractor;

    public RecipeSearchController(RecipeSearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }

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
     */
    public void execute(String recipeName, String calMin, String calMax, String carbMin, String carbMax, String proteinMin, String proteinMax, String fatMin, String fatMax) {
        final RecipeSearchInputData recipeSearchInputData = new RecipeSearchInputData(
                recipeName, calMin, calMax, carbMin, carbMax, proteinMin, proteinMax, fatMin, fatMax);

        searchUseCaseInteractor.execute(recipeSearchInputData);
    }

    /**
     * Executes the "switch to SearchResultsView" Use Case.
     */
    public void switchToSearchResultsView() {
        searchUseCaseInteractor.switchToSearchResultsView();
    }

    /**
     * Executes the "switch to ProfileView" Use Case.
     */
    public void switchToProfileView() {
        searchUseCaseInteractor.switchToProfileView();
    }

}
